package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 紧急联系人表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmergencyContact extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 紧急联系人姓名
     */
    private String name;

    /**
     * 紧急联系人电话
     */
    private String phone;

    /**
     * 紧急联系人邮箱
     */
    private String email;

    /**
     * 与老人关系
     */
    private String relation;

    /**
     * 是否接收消息（Y/N）
     */
    private String receiveFlag;


}
