package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;

public interface OutboundRecordService {
    /**
     * 分页查询出库记录
     *
     * @param pageOutboundRecordByKeyQuery
     * @return
     */
    Result pageOutboundRecordByKey(PageOutboundRecordByKeyQuery pageOutboundRecordByKeyQuery);

    /**
     * 分页搜索员工
     *
     * @param pageSearchStaffByKeyQuery
     * @return
     */
    Result pageSearchStaffByKey(PageSearchStaffByKeyQuery pageSearchStaffByKeyQuery);

    /**
     * 分页查询仓库物资
     *
     * @param pageWarehouseMaterialByKeyQuery
     * @return
     */
    Result pageWarehouseMaterialByKey(PageWarehouseMaterialByKeyQuery pageWarehouseMaterialByKeyQuery);

    /**
     * 新增出库记录
     *
     * @param addOutboundRecordQuery
     * @return
     */
    Result addOutboundRecord(AddOutboundRecordQuery addOutboundRecordQuery);

    /**
     * 根据编号查询出库记录
     *
     * @param outboundRecordId
     * @return
     */
    Result getOutboundRecordById(Long outboundRecordId);

    /**
     * 审核出库记录
     *
     * @param auditOutboundRecordQuery
     * @return
     */
    Result auditOutboundRecord(AuditOutboundRecordQuery auditOutboundRecordQuery);

    /**
     * 删除出库记录
     *
     * @param outboundRecordId
     * @return
     */
    Result deleteOutboundRecord(Long outboundRecordId);
}
