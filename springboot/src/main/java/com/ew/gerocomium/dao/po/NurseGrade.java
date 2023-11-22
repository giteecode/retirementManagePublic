package com.ew.gerocomium.dao.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 护理等级表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NurseGrade extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 级别名称
     */
    private String name;

    /**
     * 护理类型
     */
    private String type;

    /**
     * 月护理费用
     */
    private BigDecimal monthPrice;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
