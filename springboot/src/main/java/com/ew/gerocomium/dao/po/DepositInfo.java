package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 缴存药品信息表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DepositInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 药品缴存编号
     */
    private Long depositId;

    /**
     * 药品编号
     */
    private Long medicineId;

    /**
     * 缴存数量
     */
    private Integer depositNum;

    /**
     * 剩余数量
     */
    private Integer surplusNum;

    /**
     * 预警数量
     */
    private Integer warnNum;


}
