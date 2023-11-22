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
 * 咨询人表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Consult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 来源渠道编号
     */
    private Long sourceId;

    /**
     * 接待人编号
     */
    private Long staffId;

    /**
     * 咨询人姓名
     */
    private String name;

    /**
     * 咨询人电话
     */
    private String phone;

    /**
     * 与老人关系
     */
    private String relation;

    /**
     * 咨询日期
     */
    private Date consultDate;

    /**
     * 咨询内容
     */
    private String consultContent;


}
