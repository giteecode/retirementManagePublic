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
 * 护理记录表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Nurse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 护理人员编号
     */
    private Long staffId;

    /**
     * 护理时间
     */
    private Date nurseDate;

    /**
     * 护理完成情况
     */
    private String completeFlag;

    /**
     * 进餐情况
     */
    private String dineFlag;

    /**
     * 休息
     */
    private String rest;

    /**
     * 服药
     */
    private String takeMedicine;

    /**
     * 活动
     */
    private String active;


}
