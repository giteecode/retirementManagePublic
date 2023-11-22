package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Auth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级编号
     */
    private Long parentId;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限path
     */
    private String path;

    /**
     * 权限图标
     */
    private String icon;

    /**
     * 权限url
     */
    private String url;

    /**
     * 权限类别（MENU/BTN）
     */
    private String type;

    /**
     * 权限请求方式（GET/POST/PUT/DELETE）
     */
    private String method;


}
