package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 护工小组表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NurseGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 护工小组组长编号
     */
    private Long staffId;

    /**
     * 护工小组名称
     */
    private String name;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
