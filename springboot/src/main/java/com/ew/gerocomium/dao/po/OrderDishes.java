package com.ew.gerocomium.dao.po;

import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * <p>
 * 订餐食物表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订餐编号
     */
    private Long orderId;

    /**
     * 菜品名称
     */
    private String dishesName;

    /**
     * 菜品价格
     */
    private BigDecimal dishesPrice;

    /**
     * 菜品份数
     */
    private Integer orderNum;

    /**
     * 套餐标记
     */
    private String setFlag;

    /**
     * 菜品总额
     */
    private BigDecimal totalAmount;

    /**
     * 实际总额
     */
    private BigDecimal reallyAmount;


}
