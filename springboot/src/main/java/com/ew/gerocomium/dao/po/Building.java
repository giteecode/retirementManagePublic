package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 楼栋表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Building extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋名称
     */
    private String name;

    /**
     * 楼层数量
     */
    private Integer floorNum;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
