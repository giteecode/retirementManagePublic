package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;

import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 服务项目表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务项目类别编号
     */
    private Long typeId;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 收费方式
     */
    private String chargeMethod;

    /**
     * 次服务费用
     */
    private BigDecimal price;

    /**
     * 所需时间（分）
     */
    private Integer needDate;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
