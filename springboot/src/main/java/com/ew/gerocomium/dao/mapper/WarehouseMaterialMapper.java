package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.po.WarehouseMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageInventoryByKeyQuery;
import com.ew.gerocomium.dao.query.PageWarehouseMaterialByKeyQuery;
import com.ew.gerocomium.dao.vo.GetWarehouseRecordByIdVo;
import com.ew.gerocomium.dao.vo.PageInventoryByKeyVo;
import com.ew.gerocomium.dao.vo.PageWarehouseMaterialByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 入库物资表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface WarehouseMaterialMapper extends BaseMapper<WarehouseMaterial> {
    /**
     * 根据仓库编号获取该仓库所有物资库存总和
     *
     * @param warehouseId
     * @return
     */
    Long sumWarehouseMaterialNumByWarehouseId(@Param("warehouseId") Long warehouseId);

    /**
     * 根据入库记录编号列表获取入库物资列表
     *
     * @param warehouseRecordIdList
     * @return
     */
    List<DropDown> listWarehouseMaterialByWarehouseRecordIdList(@Param("warehouseRecordIdList") List<Long> warehouseRecordIdList);

    /**
     * 根据入库记录编号获取入库物资列表
     *
     * @param warehouseRecordId
     * @return
     */
    List<GetWarehouseRecordByIdVo.GetWarehouseMaterialByIdVo> listWarehouseMaterialByWarehouseRecordId(@Param("warehouseRecordId") Long warehouseRecordId);

    /**
     * 根据关键字获取仓库物资列表
     *
     * @param keyQuery
     * @return
     */
    List<PageWarehouseMaterialByKeyVo> listWarehouseMaterialByKey(@Param("keyQuery") PageWarehouseMaterialByKeyQuery keyQuery);

    /**
     * 根据关键字获取库存列表
     *
     * @param keyQuery
     * @return
     */
    List<PageInventoryByKeyVo> listInventoryByKey(@Param("keyQuery") PageInventoryByKeyQuery keyQuery);
}
