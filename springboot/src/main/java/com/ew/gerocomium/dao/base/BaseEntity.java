package com.ew.gerocomium.dao.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
