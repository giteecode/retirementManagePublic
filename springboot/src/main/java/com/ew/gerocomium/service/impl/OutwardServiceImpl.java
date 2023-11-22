package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.EmergencyContactMapper;
import com.ew.gerocomium.dao.mapper.OutwardMapper;
import com.ew.gerocomium.dao.po.EmergencyContact;
import com.ew.gerocomium.dao.po.Outward;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.dao.vo.*;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.OutwardService;
import com.ew.gerocomium.service.common.CommonFunc;
import com.ew.gerocomium.service.common.OutwardFunc;
import com.ew.gerocomium.service.common.StaffFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class OutwardServiceImpl implements OutwardService {
    @Resource
    private DepositRechargeService depositRechargeService;
    @Resource
    private OutwardMapper outwardMapper;
    @Resource
    private OutwardFunc outwardFunc;
    @Resource
    private EmergencyContactMapper emergencyContactMapper;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageOutwardByKey(PageOutwardByKeyQuery query) {
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 根据关键字获取外出登记
        List<PageOutwardByKeyVo> outwardByKeyVoList = outwardMapper.listOutwardByKey(query, startTime, endTime);
        // 封装返回数据
        PageResult<PageOutwardByKeyVo> pageResult = pageUtil.packPageResultData(outwardByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.ENTER.getStatus(), CheckEnum.EXIT_AUDIT.getStatus()));
        // 根据姓名和联系电话获取入住和退住审核老人列表
        List<PageSearchElderByKeyVo> pageSearchElderByKeyVoList = commonFunc.listPageElderByKey(query, checkFlagList);
        // 获取未被删除且未返回的外出登记
        List<Outward> outwardList = outwardFunc.listNotDelAndNotReturnOutward();
        // 根据外出登记列表过滤老人
        pageSearchElderByKeyVoList = pageSearchElderByKeyVoList.stream().filter(pageSearchElderByKeyVo -> {
            AtomicBoolean existFlag = new AtomicBoolean(false);
            outwardList.forEach(outward -> {
                if (Objects.equals(outward.getElderId(), pageSearchElderByKeyVo.getId())) {
                    existFlag.set(true);
                }
            });
            return !existFlag.get();
        }).collect(Collectors.toList());
        // 封装返回数据
        PageResult<PageSearchElderByKeyVo> pageResult = pageUtil.packPageResultData(pageSearchElderByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchStaffByKey(PageSearchStaffByKeyQuery query) {
        return commonFunc.pageSearchStaffByKeyResult(query, 5L);
    }

    @Override
    public Result listOutwardStaff() {
        return Result.success(BeanUtil.copyToList(staffFunc.listStaffByRoleId(5L), AccompanyingPersonVo.class));
    }

    @Override
    public Result pageEmergencyContact(PageSearchEmergencyContactQuery query) {
        // 根据老人编号获取紧急联系人
        List<EmergencyContact> emergencyContactList = emergencyContactMapper.selectList(new LambdaQueryWrapper<EmergencyContact>()
                .eq(EmergencyContact::getElderId, query.getElderId()));
        // 实体转换
        List<PageSearchEmergencyContactVo> pageSearchEmergencyContactVoList = BeanUtil.copyToList(emergencyContactList, PageSearchEmergencyContactVo.class);
        // 封装返回数据
        PageResult<PageSearchEmergencyContactVo> pageResult = pageUtil.packPageResultData(pageSearchEmergencyContactVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result listContactByElderId(Long elderId) {
        // 根据老人编号获取紧急联系人
        List<EmergencyContact> emergencyContactList = emergencyContactMapper.selectList(new LambdaQueryWrapper<EmergencyContact>()
                .eq(EmergencyContact::getElderId, elderId));
        // 实体转换
        List<AccompanyingPersonVo> accompanyingPersonVoList = BeanUtil.copyToList(emergencyContactList, AccompanyingPersonVo.class);
        return Result.success(accompanyingPersonVoList);
    }

    @Override
    public Result addOutward(AddOutwardQuery query) {
        // 验证是否已外出登记
        List<Outward> outwardList = outwardMapper.selectList(new LambdaQueryWrapper<Outward>()
                .eq(Outward::getElderId, query.getElderId())
                .eq(Outward::getDelFlag, YesNoEnum.NO.getCode()));
        AssertUtil.isEmpty(outwardList, ExceptionEnum.OUTWARD_REPEAT);
        // 初始化外出登记
        Outward outward = BeanUtil.toBean(query, Outward.class);
        outward.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        outwardMapper.insert(outward);
        return Result.success();
    }

    @Override
    public Result getOutwardById(Long outwardId) {
        // 根据编号获取外出登记
        GetOutwardByIdVo outwardById = outwardMapper.getOutwardById(outwardId);
        // 验证外出登记是否存在
        AssertUtil.notNull(outwardById, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(outwardById);
    }

    @Override
    public Result delayReturn(DelayReturnQuery query) {
        // 验证外出登记
        Outward outward = outwardFunc.checkOutward(query.getId(), false);
        // 封装修改
        outward.setPlanReturnDate(DateUtilWen.dateStrToDate(query.getPlanReturnDate()));
        // 修改
        outwardMapper.updateById(outward);
        return Result.success();
    }

    @Override
    public Result recordReturn(RecordReturnQuery query) {
        // 验证外出登记
        Outward outward = outwardFunc.checkOutward(query.getId(), false);
        // 验证是否已登记返回
        AssertUtil.isNull(outward.getRealReturnDate(), ExceptionEnum.ALREADY_RETURN);
        // 封装修改
        outward.setRealReturnDate(DateUtilWen.dateStrToDate(query.getRealReturnDate()));
        // 修改
        outwardMapper.updateById(outward);
        return Result.success();
    }

    @Override
    public Result deleteOutward(Long outwardId) {
        // 验证外出登记
        Outward outward = outwardFunc.checkOutward(outwardId, true);
        // 封装修改
        outward.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        outwardMapper.updateById(outward);
        return Result.success();
    }
}
