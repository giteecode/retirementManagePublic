package com.ew.gerocomium.service.common;

import com.ew.gerocomium.common.constant.ConsumeEnum;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.dao.po.Consume;
import com.ew.gerocomium.dao.vo.GetElderFeeByIdVo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 退住审核公共方法
 */
@Component
public class RetreatAuditFunc {
    /**
     * 设置时间段
     *
     * @param feeDetail
     * @param startTime
     * @param endTime
     * @return
     */
    public void setDateStr(GetElderFeeByIdVo.FeeDetail feeDetail, Date startTime, Date endTime) {
        // 获取本月合同开始时间和结束时间字符串
        String startTimeStr = DateUtilWen.dateToDateStr(startTime, "yyyy/MM/dd");
        String endTimeStr = DateUtilWen.dateToDateStr(endTime, "yyyy/MM/dd");
        // 设置时间段
        feeDetail.setFeeDate(startTimeStr + " - " + endTimeStr);
    }

    /**
     * 设置费用
     *
     * @param feeDetail
     * @param insideNurseFee
     * @param insideDishesFee
     * @param insideBedFee
     * @param payableFee
     * @param returnableFee
     * @return
     */
    public void setFee(GetElderFeeByIdVo.FeeDetail feeDetail,
                       BigDecimal insideNurseFee,
                       BigDecimal insideDishesFee,
                       BigDecimal insideBedFee,
                       BigDecimal payableFee,
                       BigDecimal returnableFee) {
        feeDetail.setContractInsideNurseFee(insideNurseFee);
        feeDetail.setContractInsideDishesFee(insideDishesFee);
        feeDetail.setContractInsideBedFee(insideBedFee);
        feeDetail.setPayableFee(payableFee);
        feeDetail.setReturnableFee(returnableFee);
    }

    /**
     * 设置时间段、护理/套餐预定费用详情
     *
     * @param feeDetail
     * @param consumeList
     * @param startTime
     * @param endTime
     * @return
     */
    public GetElderFeeByIdVo.FeeDetail setDateStrAndOrderFee(GetElderFeeByIdVo.FeeDetail feeDetail,
                                                             List<Consume> consumeList,
                                                             Date startTime,
                                                             Date endTime) {
        // 获取本月合同开始时间和结束时间字符串
        String startTimeStr = DateUtilWen.dateToDateStr(startTime, "yyyy/MM/dd");
        String endTimeStr = DateUtilWen.dateToDateStr(endTime, "yyyy/MM/dd");
        // 获取本月护理和点餐费用
        BigDecimal orderNurseAmount = new BigDecimal("0");
        BigDecimal orderDishesAmount = new BigDecimal("0");
        for (Consume consume : consumeList) {
            if (consume.getConsumeDate().after(startTime) && consume.getConsumeDate().before(endTime)) {
                if (Objects.equals(consume.getConsumeType(), ConsumeEnum.NURSE.getType())) {
                    orderNurseAmount.add(consume.getConsumeAmount());
                }
                if (Objects.equals(consume.getConsumeType(), ConsumeEnum.DISHES.getType())) {
                    orderDishesAmount.add(consume.getConsumeAmount());
                }
            }
        }
        // 设置时间段、预定护理/点餐费用
        feeDetail.setFeeDate(startTimeStr + " - " + endTimeStr);
        feeDetail.setOrderNurseFee(orderNurseAmount);
        feeDetail.setOrderDishesFee(orderDishesAmount);
        return feeDetail;
    }

    /**
     * 设置其他费用
     *
     * @param feeDetail
     * @param insideNurseFee
     * @param insideDishesFee
     * @param insideBedFee
     * @param outsideNurseFee
     * @param outsideDishesFee
     * @param outsideBedFee
     * @param payableFee
     * @param returnableFee
     * @return
     */
    public GetElderFeeByIdVo.FeeDetail setOtherFee(GetElderFeeByIdVo.FeeDetail feeDetail,
                                                   BigDecimal insideNurseFee,
                                                   BigDecimal insideDishesFee,
                                                   BigDecimal insideBedFee,
                                                   BigDecimal outsideNurseFee,
                                                   BigDecimal outsideDishesFee,
                                                   BigDecimal outsideBedFee,
                                                   BigDecimal payableFee,
                                                   BigDecimal returnableFee) {
        feeDetail.setContractInsideNurseFee(insideNurseFee);
        feeDetail.setContractInsideDishesFee(insideDishesFee);
        feeDetail.setContractInsideBedFee(insideBedFee);
        feeDetail.setContractOutsideNurseFee(outsideNurseFee);
        feeDetail.setContractOutsideDishesFee(outsideDishesFee);
        feeDetail.setContractOutsideBedFee(outsideBedFee);
        feeDetail.setPayableFee(payableFee);
        feeDetail.setReturnableFee(returnableFee);
        return feeDetail;
    }
}
