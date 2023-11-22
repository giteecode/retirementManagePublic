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
 * 入库物资表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseMaterial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库登记编号
     */
    private Long warehouseRecordId;

    /**
     * 物资编号
     */
    private Long materialId;

    /**
     * 库存数量
     */
    private Integer warehouseNum;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 有效期
     */
    private Date expireDate;


}
