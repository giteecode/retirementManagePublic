package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 老人健康数据表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HealthData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 体温
     */
    private Double temperature;

    /**
     * 心率
     */
    private Integer heartRate;

    /**
     * 收缩血压
     */
    private Integer systolicBloodPressure;

    /**
     * 舒张血压
     */
    private Integer diastolicBloodPressure;

    /**
     * 空腹血糖
     */
    private Integer fastingBloodGlucose;

    /**
     * 餐后血糖
     */
    private Integer postprandialBloodGlucose;

    /**
     * 血氧饱和度
     */
    private Integer bloodOxygenSaturation;

    /**
     * 总胆固醇
     */
    private Integer cholesterol;

    /**
     * 尿酸
     */
    private Integer uricAcid;

    /**
     * 左眼
     */
    private Double leftEye;

    /**
     * 右眼
     */
    private Double rightEye;

    /**
     * 左耳
     */
    private String leftEar;

    /**
     * 右耳
     */
    private String rightEar;

    /**
     * 肌肉率
     */
    private Integer musclePercentage;

    /**
     * 体脂率
     */
    private Integer bodyFatPercentage;

    /**
     * 腰围
     */
    private Integer waistCircumference;

    /**
     * 臀围
     */
    private Integer hipCircumference;

    /**
     * 水分率
     */
    private Integer moistureContent;


}
