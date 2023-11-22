package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.AddWarehouseRecordQuery;
import com.ew.gerocomium.dao.query.AuditWarehouseRecordQuery;
import com.ew.gerocomium.dao.query.PageWarehouseRecordByKeyQuery;

public interface WarehouseRecordService {
    /**
     * 分页查询入库记录
     *
     * @param pageWarehouseRecordByKeyQuery
     * @return
     */
    Result pageWarehouseRecordByKey(PageWarehouseRecordByKeyQuery pageWarehouseRecordByKeyQuery);

    /**
     * 获取仓库列表
     *
     * @return
     */
    Result listWarehouse();

    /**
     * 新增入库记录
     *
     * @param addWarehouseRecordQuery
     * @return
     */
    Result addWarehouseRecord(AddWarehouseRecordQuery addWarehouseRecordQuery);

    /**
     * 根据编号查询入库记录
     *
     * @param warehouseRecordId
     * @return
     */
    Result getWarehouseRecordById(Long warehouseRecordId);

    /**
     * 审核入库记录
     *
     * @param auditWarehouseRecordQuery
     * @return
     */
    Result auditWarehouseRecord(AuditWarehouseRecordQuery auditWarehouseRecordQuery);

    /**
     * 删除入库记录
     *
     * @param warehouseRecordId
     * @return
     */
    Result deleteWarehouseRecord(Long warehouseRecordId);
}
