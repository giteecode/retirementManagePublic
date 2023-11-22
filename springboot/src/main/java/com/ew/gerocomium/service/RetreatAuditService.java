package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.AuditElderFeeQuery;
import com.ew.gerocomium.dao.query.PageRetreatAuditQuery;

public interface RetreatAuditService {
    /**
     * 分页查询退住审核
     *
     * @param pageRetreatAuditQuery
     * @return
     */
    Result pageRetreatAuditByKey(PageRetreatAuditQuery pageRetreatAuditQuery);

    /**
     * 根据编号获取老人费用详情
     *
     * @param elderId
     * @return
     */
    Result getElderFeeById(Long elderId);

    /**
     * @param auditElderFeeQuery
     * @return
     */
    Result auditElderFee(AuditElderFeeQuery auditElderFeeQuery);
}
