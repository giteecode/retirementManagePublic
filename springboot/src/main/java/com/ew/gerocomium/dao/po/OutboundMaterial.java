package com.ew.gerocomium.dao.po;

import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 出库物资表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OutboundMaterial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 出库登记编号
     */
    private Long outboundRecordId;

    /**
     * 入库物资编号
     */
    private Long warehouseMaterialId;

    /**
     * 物资编号
     */
    private Long materialId;

    /**
     * 出库数量
     */
    private Integer outboundNum;


}
