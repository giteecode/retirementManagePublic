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
 * 来访登记表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 来访者姓名
     */
    private String name;

    /**
     * 来访者电话
     */
    private String phone;

    /**
     * 与老人关系
     */
    private String relation;

    /**
     * 来访时间
     */
    private Date visitDate;

    /**
     * 离开时间
     */
    private Date leaveDate;

    /**
     * 来访者人数
     */
    private Integer visitNum;

    /**
     * 来访状态
     */
    private String visitFlag;

    /**
     * 删除状态
     */
    private String delFlag;


}
