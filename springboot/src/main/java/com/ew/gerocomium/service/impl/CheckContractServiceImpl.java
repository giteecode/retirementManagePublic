package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.*;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.OperateCheckContractQuery;
import com.ew.gerocomium.dao.query.PageCheckContractByKeyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;
import com.ew.gerocomium.dao.vo.GetBedByIdVo;
import com.ew.gerocomium.dao.vo.GetCheckContractByIdVo;
import com.ew.gerocomium.dao.vo.PageCheckContractByKeyVo;
import com.ew.gerocomium.dao.vo.TreeVo;
import com.ew.gerocomium.service.CheckContractService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CheckContractServiceImpl implements CheckContractService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private ReserveMapper reserveMapper;
    @Resource
    private BedFunc bedFunc;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private ContractFunc contractFunc;
    @Resource
    private NurseGradeFunc nurseGradeFunc;
    @Resource
    private CateringSetFunc cateringSetFunc;
    @Resource
    private EmergencyContactMapper emergencyContactMapper;
    @Resource
    private EmergencyContactFunc emergencyContactFunc;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageCheckContractByKey(PageCheckContractByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.ENTER.getStatus(), CheckEnum.EXIT_AUDIT.getStatus()));
        // 根据条件获取老人信息
        List<Elder> listElderByKey = elderFunc.listElderByKey(query.getName(), query.getSex(), query.getIdNum(), checkFlagList);
        // 实体转换
        List<PageCheckContractByKeyVo> pageCheckContractByKeyVoList = BeanUtil.copyToList(listElderByKey, PageCheckContractByKeyVo.class);
        // 封装返回数据
        PageResult<PageCheckContractByKeyVo> pageResult = pageUtil.packPageResultData(pageCheckContractByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.CONSULT.getStatus(), CheckEnum.INTENTION.getStatus(), CheckEnum.RESERVE.getStatus(), CheckEnum.EXIT.getStatus()));
        // 根据姓名和联系电话获取咨询中、意向跟进、预定、已退住老人列表
        return commonFunc.pageSearchElderByKeyResult(query, checkFlagList);
    }

    @Override
    public Result listNurseGrade() {
        // 获取未被删除的护理等级列表
        List<NurseGrade> nurseGradeList = nurseGradeFunc.listNotDelNurseGrade();
        // 转换实体
        List<DropDown> dropDownList = BeanUtil.copyToList(nurseGradeList, DropDown.class);
        return Result.success(dropDownList);
    }

    @Override
    public Result listCateringSet() {
        // 获取未被删除的餐饮套餐列表
        List<CateringSet> cateringSetList = cateringSetFunc.listNotDelCateringSet();
        // 转换实体
        List<DropDown> dropDownList = BeanUtil.copyToList(cateringSetList, DropDown.class);
        return Result.success(dropDownList);
    }

    @Override
    public Result getBuildTree() {
        return commonFunc.getBuildingTreeResult(false);
    }

    @Override
    public Result getBedById(Long bedId) {
        return Result.success(bedMapper.getBedById(bedId));
    }

    @Override
    @Transactional
    public Result addCheckContract(OperateCheckContractQuery query) {
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
            query.setId(null);
            Elder elder = BeanUtil.toBean(query, Elder.class);
            elder.setBalance(BigDecimal.valueOf(0));
            elder.setCheckFlag(CheckEnum.ENTER.getStatus());
            // 新增
            elderMapper.insert(elder);
            elderByIdNum = elder;
        } else {
            // 判断该老人状态
            elderFunc.checkEnterOrExitAudit(elderByIdNum);
            // 老人状态为预定 && 老人预定床位编号和传入床位编号不相等
            boolean editFlag = Objects.equals(elderByIdNum.getCheckFlag(), CheckEnum.RESERVE.getStatus()) &&
                    !Objects.equals(elderByIdNum.getBedId(), query.getBedId());
            // 将之前预定床位状态修改为空闲
            if (editFlag) {
                // 根据编号获取之前预定床位
                Bed temporaryBed = bedMapper.selectById(elderByIdNum.getBedId());
                // 床位存在 && 床位状态为预定
                if (ObjUtil.isNotEmpty(temporaryBed) && Objects.equals(temporaryBed.getBedFlag(), BedEnum.RESERVE.getStatus())) {
                    temporaryBed.setBedFlag(BedEnum.IDLE.getStatus());
                    bedMapper.updateById(temporaryBed);
                }
            }
            // 封装修改
            query.setId(elderByIdNum.getId());
            BeanUtil.copyProperties(query, elderByIdNum);
            elderByIdNum.setCheckFlag(CheckEnum.ENTER.getStatus());
            // 修改
            elderMapper.updateById(elderByIdNum);
        }
        // 封装床位入住修改
        bed.setBedFlag(BedEnum.ENTER.getStatus());
        // 修改床位入住
        bedMapper.updateById(bed);
        // 批量插入紧急联系人
        emergencyContactFunc.saveBatchEmergencyContact(query, elderByIdNum.getId(), false);
        // 新增合同
        contractFunc.insertContract(query, elderByIdNum.getId());
        // 删除老人预定记录
        reserveMapper.delete(new LambdaQueryWrapper<Reserve>().eq(Reserve::getElderId, elderByIdNum.getId()));
        return Result.success();
    }

    @Override
    public Result getCheckContractById(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 实体转换
        GetCheckContractByIdVo getCheckContractByIdVo = BeanUtil.toBean(elder, GetCheckContractByIdVo.class);
        // 根据老人编号获取合同
        Contract contract = contractMapper.selectOne(new LambdaQueryWrapper<Contract>()
                .eq(Contract::getElderId, elderId));
        if (ObjUtil.isNotEmpty(contract)) {
            getCheckContractByIdVo.setStaffId(contract.getStaffId());
            getCheckContractByIdVo.setSignDate(DateUtilWen.dateToDateStr(contract.getSignDate(), "yyyy/MM/dd hh:mm:ss"));
            getCheckContractByIdVo.setStartDate(DateUtilWen.dateToDateStr(contract.getStartDate(), "yyyy/MM/dd hh:mm:ss"));
            getCheckContractByIdVo.setEndDate(DateUtilWen.dateToDateStr(contract.getEndDate(), "yyyy/MM/dd hh:mm:ss"));
        }
        // 设置老人的紧急联系人列表
        getCheckContractByIdVo.setOperateEmergencyContactQueryList(emergencyContactFunc.listEmgencyContactByElderId(elderId));
        return Result.success(getCheckContractByIdVo);
    }

    @Override
    @Transactional
    public Result editCheckContract(OperateCheckContractQuery query) {
        // 验证老人
        Elder elder = elderFunc.checkElder(query.getId(), query.getIdNum());
        // 若床位编号不一致
        if (!Objects.equals(elder.getBedId(), query.getBedId())) {
            // 判断床位是否被占用
            Bed bed = bedFunc.getBedById(query.getBedId());
            // 验证床位是否存在
            AssertUtil.notNull(bed, ExceptionEnum.BED_NULL);
            // 验证床位是否被占用
            AssertUtil.isTrue(Objects.equals(bed.getBedFlag(), BedEnum.IDLE.getStatus()), ExceptionEnum.BED_OCCUPY);
            // 将之前选择的床位设置为空闲
            Bed idleBed = new Bed();
            idleBed.setId(elder.getBedId());
            idleBed.setBedFlag(BedEnum.IDLE.getStatus());
            // 修改
            bedMapper.updateById(idleBed);
            // 将此次选择的床位设置为入住
            Bed enterBed = new Bed();
            enterBed.setId(query.getBedId());
            enterBed.setBedFlag(BedEnum.ENTER.getStatus());
            // 修改
            bedMapper.updateById(enterBed);
        }
        // 封装修改老人
        BeanUtil.copyProperties(query, elder);
        // 修改
        elderMapper.updateById(elder);
        // 批量插入紧急联系人
        emergencyContactFunc.saveBatchEmergencyContact(query, elder.getId(), true);
        // 新增合同
        contractFunc.insertContract(query, elder.getId());
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteCheckContract(Long elderId) {
        // 验证老人
        Elder elder = elderFunc.checkElder(elderId, null);
        // 根据编号逻辑删除老人
        elderMapper.deleteElderById(elderId);
        // 封装床位修改
        Bed bed = new Bed();
        bed.setId(elder.getBedId());
        bed.setBedFlag(BedEnum.IDLE.getStatus());
        // 修改床位
        bedMapper.updateById(bed);
        return Result.success();
    }
}
