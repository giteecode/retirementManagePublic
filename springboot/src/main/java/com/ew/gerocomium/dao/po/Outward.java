package com.ew.gerocomium.dao.po;

import java.util.Date;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 外出登记表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Outward extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 陪同人姓名
     */
    private String chaperoneName;

    /**
     * 陪同人电话
     */
    private String chaperonePhone;

    /**
     * 陪同人类型（家属/护工）
     */
    private String chaperoneType;

    /**
     * 外出时间
     */
    private Date outwardDate;

    /**
     * 计划返回时间
     */
    private Date planReturnDate;

    /**
     * 实际返回时间
     */
    private Date realReturnDate;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
