package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 护理项目表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NurseItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 护理等级编号
     */
    private Long gradeId;

    /**
     * 服务项目编号
     */
    private Long serviceId;


}
