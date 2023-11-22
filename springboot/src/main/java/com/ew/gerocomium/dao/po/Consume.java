package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 消费记录表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Consume extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 消费类别
     */
    private String consumeType;

    /**
     * 消费金额
     */
    private BigDecimal consumeAmount;

    /**
     * 消费日期
     */
    private Date consumeDate;

}
