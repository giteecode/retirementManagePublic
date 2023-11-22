package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Label extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类别编号
     */
    private Long typeId;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
