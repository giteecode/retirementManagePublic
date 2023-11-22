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
 * 事故登记表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Accident extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 值班护工编号
     */
    private Long staffId;

    /**
     * 发生时间
     */
    private Date occurDate;

    /**
     * 事故描述
     */
    private String description;

    /**
     * 事故图片
     */
    private String picture;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
