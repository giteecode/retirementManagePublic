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
 * 回访计划表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitPlan extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    private Long elderId;

    /**
     * 回访计划标题
     */
    private String title;

    /**
     * 计划回访时间
     */
    private Date planDate;

    /**
     * 回访计划内容
     */
    private String content;

    /**
     * 计划完成时间
     */
    private Date completeDate;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
