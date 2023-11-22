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
 * 老人表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Elder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 护理等级编号
     */
    private Long nursingGradeId;

    /**
     * 餐饮套餐编号
     */
    private Long cateringSetId;

    /**
     * 床位编号
     */
    private Long bedId;

    /**
     * 老人姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(男/女)
     */
    private String sex;

    /**
     * 老人电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 入住状态
     */
    private String checkFlag;


}
