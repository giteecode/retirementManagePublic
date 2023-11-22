package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 预定表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Reserve extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 销售人员编号
     */
    private Long staffId;

    /**
     * 交款人姓名
     */
    private String name;

    /**
     * 交款人电话
     */
    private String phone;

    /**
     * 预定到期时间
     */
    private Date dueDate;

    /**
     * 定金
     */
    private BigDecimal deposit;

    /**
     * 预定状态
     */
    private String reserveFlag;


}
