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
 * 物资表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Material extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物资类别编号
     */
    private Long typeId;

    /**
     * 物资名称
     */
    private String name;

    /**
     * 物资单价
     */
    private BigDecimal price;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
