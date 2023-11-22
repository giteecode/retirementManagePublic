package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 老人健康信息表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HealthInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 自理情况
     */
    private String selfCare;

    /**
     * 视力
     */
    private String vision;

    /**
     * 听力
     */
    private String hearing;

    /**
     * 主治医院
     */
    private String hospital;

    /**
     * 主治医师
     */
    private String doctor;

    /**
     * 医院电话
     */
    private String phone;

    /**
     * 过敏药物
     */
    private String allergyDrug;

    /**
     * 病史
     */
    private String medicalHistory;

    /**
     * 主要疾病
     */
    private String majorDisease;


}
