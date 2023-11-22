package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.BuildingVo;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.mapper.ReserveMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.GetReserveByReserveIdAndElderIdQuery;
import com.ew.gerocomium.dao.query.AddReserveQuery;
import com.ew.gerocomium.dao.query.PageReserveByKeyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;
import com.ew.gerocomium.dao.vo.ExpireReserveVo;
import com.ew.gerocomium.dao.vo.GetReserveByReserveIdAndElderIdVo;
import com.ew.gerocomium.dao.vo.PageReserveByKeyVo;
import com.ew.gerocomium.dao.vo.TreeVo;
import com.ew.gerocomium.service.ReserveService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ReserveServiceImpl implements ReserveService {
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private BuildingFunc buildingFunc;
    @Resource
    private FloorFunc floorFunc;
    @Resource
    private RoomFunc roomFunc;
    @Resource
    private BedFunc bedFunc;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private ReserveMapper reserveMapper;
    @Resource
    private ReserveFunc reserveFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageReserveByKey(PageReserveByKeyQuery query) {
        // 根据条件获取老人信息
        List<PageReserveByKeyVo> pageReserveByKeyVoList = reserveMapper.listReserveByKey(query);
        // 封装返回数据
        PageResult<PageReserveByKeyVo> pageResult = pageUtil.packPageResultData(pageReserveByKeyVoList, query.getPageNum(), query.getPageSize());
        // 替换退款状态
        pageResult.getList().forEach(pageReserveByKeyVo -> pageReserveByKeyVo.setReserveFlag(
                Objects.equals(pageReserveByKeyVo.getReserveFlag(), YesNoEnum.NO.getCode()) ?
                        "未退款" :
                        "已退款"
        ));
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.CONSULT.getStatus(), CheckEnum.INTENTION.getStatus(), CheckEnum.EXIT.getStatus()));
        // 根据姓名和联系电话获取咨询中、意向跟进、已退住老人列表
        return commonFunc.pageSearchElderByKeyResult(query, checkFlagList);
    }

    @Override
    public Result getBuildTree() {
        return commonFunc.getBuildingTreeResult(true);
    }

    @Override
    @Transactional
    public Result addReserve(AddReserveQuery query) {
        // 根据编号获取床位
        Bed bed = bedFunc.getBedById(query.getBedId());
        // 验证床位是否存在
        AssertUtil.notNull(bed, ExceptionEnum.BED_NULL);
        // 验证床位是否被占用
        AssertUtil.isTrue(Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus()), ExceptionEnum.BED_OCCUPY);
        // 根据身份证号获取老人信息
        Elder elderByIdNum = elderFunc.getElderByIdNum(query.getIdNum());
        if (ObjUtil.isEmpty(elderByIdNum)) {
            // 初始化老人
            Elder elder = elderFunc.operateReserveInitElder(query, true);
            // 新增
            elderMapper.insert(elder);
            // 初始化预定
            Reserve reserve = reserveFunc.operateReserveInitElder(query, elder.getId());
            // 新增
            reserveMapper.insert(reserve);
        } else {
            // 判断该老人状态
            elderFunc.checkReserve(elderByIdNum);
            // 封装老人修改
            Elder elder = elderFunc.operateReserveInitElder(query, false);
            elder.setId(elderByIdNum.getId());
            // 修改老人
            elderMapper.updateById(elder);
            // 删除老人预定记录
            reserveMapper.delete(new LambdaQueryWrapper<Reserve>()
                    .eq(Reserve::getElderId, elder.getId()));
            // 初始化预定
            Reserve reserve = reserveFunc.operateReserveInitElder(query, elder.getId());
            // 新增
            reserveMapper.insert(reserve);
        }
        // 封装床位预定修改
        bed.setBedFlag(BedEnum.RESERVE.getStatus());
        // 修改床位预定
        bedMapper.updateById(bed);
        return Result.success();
    }

    @Override
    public Result getReserveByReserveIdAndElderId(GetReserveByReserveIdAndElderIdQuery query) {
        // 根据编号获取预定
        GetReserveByReserveIdAndElderIdVo getReserveByReserveIdAndElderIdVo = reserveMapper.getReserveByReserveIdAndElderId(query);
        // 判断是否为空
        AssertUtil.notNull(getReserveByReserveIdAndElderIdVo, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(getReserveByReserveIdAndElderIdVo);
    }

    @Override
    @Transactional
    public Result refund(Long reserveId) {
        // 根据编号预定
        Reserve reserve = reserveMapper.selectById(reserveId);
        // 判断是否为空
        AssertUtil.notNull(reserve, ExceptionEnum.DATA_NOT_EXIST);
        // 判断是否已退款
        AssertUtil.notTrue(Objects.equals(reserve.getReserveFlag(), YesNoEnum.YES.getCode()), ExceptionEnum.REFUND_REPEAT);
        // 判断是否已过期
        AssertUtil.notTrue(DateUtilWen.getDayEndTime(reserve.getDueDate()).before(new Date()), ExceptionEnum.DUE_DATE_EXPIRE);
        // 封装修改预定
        reserve.setReserveFlag(YesNoEnum.YES.getCode());
        // 修改预定
        reserveMapper.updateById(reserve);
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(reserve.getElderId());
        // 判断该老人状态
        elderFunc.checkEnterOrExitAudit(elder);
        // 如果老人状态为预定
        if (Objects.equals(elder.getCheckFlag(), CheckEnum.RESERVE.getStatus())) {
            // 修改老人
            elderMapper.cancelReserveByElderIdList(new ArrayList<>(Collections.singletonList(elder.getId())));
        }
        // 根据编号获取床位
        Bed bed = bedMapper.selectById(elder.getBedId());
        // 如果床位状态为预定
        if (Objects.equals(bed.getBedFlag(), BedEnum.RESERVE.getStatus())) {
            // 封装修改床位
            bed.setBedFlag(BedEnum.IDLE.getStatus());
            // 修改床位
            bedMapper.updateById(bed);
        }
        return Result.success();
    }

    @Override
    @Transactional
    public void reserveExpireJob() {
        // 获取前一天当前时间
        Date nowDayAroundTime = DateUtilWen.getNowDayAroundTime(new Date(), -1);
        // 获取前一天结束时间
        Date expireTime = DateUtilWen.getDayEndTime(nowDayAroundTime);
        // 获取过期预定列表
        List<ExpireReserveVo> expireReserveVoList = reserveMapper.listExpireReserve(expireTime);
        // 验证预定列表是否为空
        if (ObjUtil.isEmpty(expireReserveVoList)) {
            return;
        }
        // 实例化删除预定编号列表
        List<Long> reserveIdList = new ArrayList<>();
        // 实例化修改老人编号列表
        List<Long> elderIdList = new ArrayList<>();
        // 实例化修改床位列表
        List<Bed> bedList = new ArrayList<>();
        expireReserveVoList.forEach(expireReserveVo -> {
            reserveIdList.add(expireReserveVo.getReserveId());
            // 若老人状态为预定
            if (Objects.equals(expireReserveVo.getCheckFlag(), CheckEnum.RESERVE.getStatus())) {
                elderIdList.add(expireReserveVo.getElderId());
            }
            // 若床位状态为预定
            if (Objects.equals(expireReserveVo.getBedFlag(), BedEnum.RESERVE.getStatus())) {
                Bed bed = new Bed();
                bed.setId(expireReserveVo.getBedId());
                bed.setBedFlag(BedEnum.IDLE.getStatus());
                bedList.add(bed);
            }
        });
        // 批量删除过期预定
        if (ObjUtil.isNotEmpty(reserveIdList)) {
            reserveMapper.deleteBatchIds(reserveIdList);
        }
        // 批量修改老人
        if (ObjUtil.isNotEmpty(elderIdList)) {
            elderMapper.cancelReserveByElderIdList(elderIdList);
        }
        // 批量修改床位
        if (ObjUtil.isNotEmpty(bedList)) {
            bedFunc.updateBatchById(bedList);
        }
    }
}
