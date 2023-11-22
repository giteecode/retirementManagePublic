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
 * 菜品表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜品类别编号
     */
    private Long typeId;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 菜品价格
     */
    private BigDecimal price;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
