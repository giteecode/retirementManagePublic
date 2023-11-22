package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订餐表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`order`")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 送餐人编号
     */
    private Long staffId;

    /**
     * 送餐时间
     */
    private Date deliverDishesDate;

    /**
     * 就餐时间
     */
    private Date dineDate;

    /**
     * 就餐方式
     */
    private String dineType;

    /**
     * 支付总额
     */
    private BigDecimal payAmount;

    /**
     * 订单状态
     */
    private String orderFlag;


}
