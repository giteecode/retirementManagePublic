package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 餐饮套餐表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CateringSet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 餐饮套餐名称
     */
    private String name;

    /**
     * 月套餐费用
     */
    private BigDecimal monthPrice;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
