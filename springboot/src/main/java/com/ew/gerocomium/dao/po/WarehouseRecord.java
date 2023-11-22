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
 * 入库登记表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseRecord extends BaseEntity {

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
     * 物资来源
     */
    private String source;

    /**
     * 入库时间
     */
    private Date warehouseDate;

    /**
     * 入库状态
     */
    private String warehouseFlag;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
