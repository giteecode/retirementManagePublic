package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Staff extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(男/女)
     */
    private String sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String pass;

    /**
     * 头像
     */
    private String avator;

    /**
     * 地址
     */
    private String address;

    /**
     * 离职状态（Y/N）
     */
    private String leaveFlag;


}
