package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 护理预定表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NurseReserve extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 服务人编号
     */
    private Long staffId;

    /**
     * 服务项目名称
     */
    private String serviceName;

    /**
     * 所需时间
     */
    private Integer needDate;

    /**
     * 服务费用
     */
    private BigDecimal servicePrice;

    /**
     * 收费方式
     */
    private String chargeMethod;

    /**
     * 服务次数
     */
    private Integer frequency;

    /**
     * 支付总额
     */
    private BigDecimal payAmount;

    /**
     * 订单状态
     */
    private String orderFlag;

    /**
     * 护理时间
     */
    private Date nurseDate;


}
