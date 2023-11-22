package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 出库登记表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    private Long warehouseId;

    /**
     * 经办人编号
     */
    private Long staffId;

    /**
     * 领用人编号
     */
    private Long recipientId;

    /**
     * 领用人类型
     */
    private String recipientType;

    /**
     * 物资去向
     */
    private String materialUse;

    /**
     * 出库时间
     */
    private Date outboundDate;

    /**
     * 出库状态
     */
    private String outboundFlag;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
