package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用药设置表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicineSet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 药品缴存信息编号
     */
    private Long depositInfoId;

    /**
     * 用药时间（餐前/餐后）
     */
    private String medicineTime;

    /**
     * 天频率
     */
    private Integer dayFrequency;


}
