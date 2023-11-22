package com.ew.gerocomium.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.*;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.mapper.ConsumeMapper;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.mapper.RetreatApplyMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.AuditElderFeeQuery;
import com.ew.gerocomium.dao.query.PageRetreatAuditQuery;
import com.ew.gerocomium.dao.vo.*;
import com.ew.gerocomium.service.RetreatAuditService;
import com.ew.gerocomium.service.common.CateringSetFunc;
import com.ew.gerocomium.service.common.ContractFunc;
import com.ew.gerocomium.service.common.NurseGradeFunc;
import com.ew.gerocomium.service.common.RetreatAuditFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class RetreatAuditServiceImpl implements RetreatAuditService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private RetreatApplyMapper retreatApplyMapper;
    @Resource
    private NurseGradeFunc nurseGradeFunc;
    @Resource
    private CateringSetFunc cateringSetFunc;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private ContractFunc contractFunc;
    @Resource
    private ConsumeMapper consumeMapper;
    @Resource
    private RetreatAuditFunc retreatAuditFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageRetreatAuditByKey(PageRetreatAuditQuery query) {
        // 根据搜索关键字查询退住申请信息
        List<PageRetreatByKeyVo> pageRetreatByKeyVoList = retreatApplyMapper.listRetreatAuditByKey(query);
        // 封装返回数据
        PageResult<PageRetreatByKeyVo> pageResult = pageUtil.packPageResultData(pageRetreatByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result getElderFeeById(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 根据老人编号获取合同
        Contract contract = contractFunc.getContractByElderId(elderId);
        // 根据编号获取护理等级
        GetNurseGradeByIdVo nurse = nurseGradeFunc.getNurseGradeById(elder.getNursingGradeId());
        // 根据编号获取餐饮套餐
        GetCateringSetByIdVo dishes = cateringSetFunc.getCateringSetById(elder.getCateringSetId());
        // 根据编号获取床位
        GetBedByIdVo bed = bedMapper.getBedById(elder.getBedId());

        // 获取合同开始时间
        Date startTime = DateUtilWen.getDayStartTime(contract.getStartDate());
        // 获取合同结束时间
        Date endTime = DateUtilWen.getDayEndTime(contract.getEndDate());
        // 获取当前时间
        Date nowTime = DateUtilWen.getDayEndTime(new Date());

        // 封装数据
        BigDecimal zero = new BigDecimal("0");
        BigDecimal two = new BigDecimal("2");
        // 满月费用
        BigDecimal nurseMonthPrice = nurse.getMonthPrice();
        BigDecimal dishesMonthPrice = dishes.getMonthPrice();
        BigDecimal bedMonthPrice = bed.getMonthPrice();
        BigDecimal monthFee = nurseMonthPrice.add(dishesMonthPrice).add(bedMonthPrice);
        // 半月费用
        BigDecimal nurseFee = nurseMonthPrice.divide(two);
        BigDecimal dishesFee = dishesMonthPrice.divide(two);
        BigDecimal bedFee = bedMonthPrice.divide(two);
        BigDecimal fee = nurseFee.add(dishesFee).add(bedFee);
        // 初始化老人消费详情
        GetElderFeeByIdVo getElderFeeByIdVo = new GetElderFeeByIdVo();
        getElderFeeByIdVo.setElderName(elder.getName());
        getElderFeeByIdVo.setContractStartTime(contract.getStartDate());
        getElderFeeByIdVo.setContractEndTime(contract.getEndDate());
        List<GetElderFeeByIdVo.FeeDetail> feeDetailList = new ArrayList<>();

        // TODO 重新设计统计思路
        // TODO 1.合同未开始（当前时间在合同开始时间之前）
        if (nowTime.before(startTime)) {
            // TODO 该合同开始时间在合同结束时间之前
            while (startTime.before(endTime)) {
                // 初始化费用
                GetElderFeeByIdVo.FeeDetail feeDetail = new GetElderFeeByIdVo.FeeDetail();
                // 根据当月开始时间获取下月开始时间
                Date nextMonthStartTime = DateUtilWen.getNowMonthAroundTime(startTime, 1);
                // 根据下月合同开始时间获取当月合同结束时间
                Date monthEndTime = DateUtilWen.getDayEndTime(
                        DateUtilWen.getNowDayAroundTime(nextMonthStartTime, -1)
                );
                // TODO 1.1.该月合同结束时间在合同结束时间之前
                if (monthEndTime.before(endTime)) {
                    // 设置时间段
                    retreatAuditFunc.setDateStr(feeDetail, startTime, monthEndTime);
                    // 设置费用
                    retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, monthFee);
                    // TODO 1.2.该月合同结束时间在合同结束时间之后
                } else {
                    // 该月合同结束时间设置为合同结束时间
                    monthEndTime = endTime;
                    // 获取该月合同天数
                    int contractDay = Math.toIntExact(DateUtil.betweenDay(startTime, monthEndTime, true));
                    // 设置时间段
                    retreatAuditFunc.setDateStr(feeDetail, startTime, monthEndTime);
                    // 设置费用
                    if (contractDay <= 15) {
                        retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, fee);
                    } else {
                        retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, monthFee);
                    }
                }
                // 添加至列表
                feeDetailList.add(feeDetail);
                // 设置下月开始时间
                startTime = nextMonthStartTime;
            }
            // TODO 2.合同已开始（当前时间在合同开始时间之后）
        } else {
            // TODO 2.1.合同未到期（当前时间在合同结束时间之前）
            if (nowTime.before(endTime)) {
                boolean returnFlag = false;
                // TODO 该合同开始时间在合同结束时间之前
                while (startTime.before(endTime)) {
                    // 初始化费用
                    GetElderFeeByIdVo.FeeDetail feeDetail = new GetElderFeeByIdVo.FeeDetail();
                    // 根据当月开始时间获取下月开始时间
                    Date nextMonthStartTime = DateUtilWen.getNowMonthAroundTime(startTime, 1);
                    // 根据下月合同开始时间获取当月合同结束时间
                    Date monthEndTime = DateUtilWen.getDayEndTime(
                            DateUtilWen.getNowDayAroundTime(nextMonthStartTime, -1)
                    );

                    // 当前时间在该月合同结束时间之后
                    if (monthEndTime.after(endTime)) {
                        // 该月合同结束时间设置为合同结束时间
                        monthEndTime = endTime;
                    }
                    // 设置时间段
                    retreatAuditFunc.setDateStr(feeDetail, startTime, monthEndTime);
                    // TODO 2.1.1.1.当前时间在该月合同结束时间之前
                    if (nowTime.before(monthEndTime)) {
                        // 获取该月合同天数
                        int contractDay = Math.toIntExact(DateUtil.betweenDay(startTime, monthEndTime, true));
                        // 未开始返还
                        if (!returnFlag) {
                            returnFlag = true;
                            // 获取该月入住天数
                            int checkDay = Math.toIntExact(DateUtil.betweenDay(startTime, nowTime, true));
                            if (checkDay <= 15) {
                                if(contractDay<=15){
                                    retreatAuditFunc.setFee(feeDetail, nurseFee, dishesFee, bedFee, zero, zero);
                                }else {
                                    if (contractDay - checkDay <= 15) {
                                        retreatAuditFunc.setFee(feeDetail, nurseFee, dishesFee, bedFee, zero, fee);
                                    } else {
                                        retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, monthFee);
                                    }
                                }
                            } else {
                                retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, zero, zero);
                            }
                            // 已开始返还
                        } else {
                            if (contractDay <= 15) {
                                retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, fee);
                            } else {
                                retreatAuditFunc.setFee(feeDetail, zero, zero, zero, zero, monthFee);
                            }
                        }
                        // TODO 2.1.1.2.当前时间在该月合同结束时间之后
                    } else {
                        retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, zero, zero);
                    }

                    feeDetailList.add(feeDetail);
                    // 设置下月开始时间
                    startTime = nextMonthStartTime;
                }
                // TODO 2.2.合同已到期（当前时间在合同结束时间之后）
            } else {
                boolean payFlag = false;
                // TODO 该合同开始时间在当前时间之前
                while (startTime.before(nowTime)) {
                    // 初始化费用
                    GetElderFeeByIdVo.FeeDetail feeDetail = new GetElderFeeByIdVo.FeeDetail();
                    // 根据当月开始时间获取下月开始时间
                    Date nextMonthStartTime = DateUtilWen.getNowMonthAroundTime(startTime, 1);
                    // 根据下月合同开始时间获取当月合同结束时间
                    Date monthEndTime = DateUtilWen.getDayEndTime(
                            DateUtilWen.getNowDayAroundTime(nextMonthStartTime, -1)
                    );

                    // 该月合同结束时间在当前时间之后
                    if (monthEndTime.after(nowTime)) {
                        // 该月合同结束时间设置为当前时间
                        monthEndTime = nowTime;
                    }
                    // 设置时间段
                    retreatAuditFunc.setDateStr(feeDetail, startTime, monthEndTime);
                    // TODO 2.2.1.1.合同结束时间在该月合同结束时间之前
                    if (endTime.before(monthEndTime)) {
                        // 获取该月合同天数
                        int contractDay = Math.toIntExact(DateUtil.betweenDay(startTime, monthEndTime, true));
                        if (!payFlag) {
                            payFlag = true;
                            // 获取该月合同已支付天数
                            int checkDay = Math.toIntExact(DateUtil.betweenDay(startTime, endTime, true));
                            if (checkDay <= 15) {
                                if(contractDay<=15){
                                    retreatAuditFunc.setFee(feeDetail, nurseFee, dishesFee, bedFee, zero, zero);
                                }else {
                                    if (contractDay - checkDay <= 15) {
                                        retreatAuditFunc.setFee(feeDetail, nurseFee, dishesFee, bedFee, zero, zero);
                                    } else {
                                        retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, monthFee, zero);
                                    }
                                }
                            } else {
                                retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, zero, zero);
                            }
                        } else {
                            if (contractDay <= 15) {
                                retreatAuditFunc.setFee(feeDetail, nurseFee, dishesFee, bedFee, fee, zero);
                            } else {
                                retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, monthFee, zero);
                            }
                        }
                        // TODO 2.2.1.2.合同结束时间在该月合同结束时间之后
                    } else {
                        retreatAuditFunc.setFee(feeDetail, nurseMonthPrice, dishesMonthPrice, bedMonthPrice, zero, zero);
                    }

                    feeDetailList.add(feeDetail);
                    // 设置下月开始时间
                    startTime = nextMonthStartTime;
                }
            }
        }

        getElderFeeByIdVo.setFeeDetailList(feeDetailList);
        return Result.success(getElderFeeByIdVo);
    }

    @Override
    @Transactional
    public Result auditElderFee(AuditElderFeeQuery query) {
        // 验证审核结果是否合法
        boolean checkAuditResult = !Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus()) &&
                !Objects.equals(query.getAuditResult(), AuditEnum.NO_PASS.getStatus());
        AssertUtil.notTrue(checkAuditResult, ExceptionEnum.AUDIT_RESULT_ERROR);
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(query.getElderId());
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 验证老人是否为退住审核状态
        AssertUtil.isTrue(Objects.equals(elder.getCheckFlag(), CheckEnum.EXIT_AUDIT.getStatus()), ExceptionEnum.ELDER_NOT_EXIT_AUDIT);
        // 验证审核是否通过
        boolean passFlag = Objects.equals(query.getAuditResult(), AuditEnum.PASS.getStatus());
        // 封装申请修改
        RetreatApply retreatApply = new RetreatApply();
        retreatApply.setId(query.getApplyId());
        retreatApply.setApplyFlag(passFlag ? AuditEnum.HAVE_PASS.getStatus() : AuditEnum.NOT_PASS.getStatus());
        // 修改
        retreatApplyMapper.updateById(retreatApply);
        // 若通过
        if (passFlag) {
            // 修改老人信息
            elderMapper.retreatElderById(query.getElderId());
            // 若不通过
        } else {
            // 封装老人修改
            elder.setCheckFlag(CheckEnum.ENTER.getStatus());
            // 修改
            elderMapper.updateById(elder);
        }
        // 封装床位修改
        Bed bed = new Bed();
        bed.setId(elder.getBedId());
        bed.setBedFlag(passFlag ? BedEnum.IDLE.getStatus() : BedEnum.ENTER.getStatus());
        // 修改
        bedMapper.updateById(bed);
        return Result.success();
    }
}
