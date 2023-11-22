package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageRetreatApplyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;

public interface RetreatApplyService {
    /**
     * 分页查询退住申请
     *
     * @param pageRetreatApplyQuery
     * @return
     */
    Result pageRetreatApplyByKey(PageRetreatApplyQuery pageRetreatApplyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 新增退住申请
     *
     * @param elderId
     * @return
     */
    Result addRetreatApply(Long elderId);
}
