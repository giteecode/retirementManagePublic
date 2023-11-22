package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 套餐食物表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetDishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 餐饮套餐编号
     */
    private Long setId;

    /**
     * 菜品食物编号
     */
    private Long dishesId;


}
