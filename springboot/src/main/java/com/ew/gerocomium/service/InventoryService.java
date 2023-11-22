package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageInventoryByKeyQuery;

public interface InventoryService {
    /**
     * 分页查询库存
     *
     * @param pageInventoryByKeyQuery
     * @return
     */
    Result pageInventoryByKey(PageInventoryByKeyQuery pageInventoryByKeyQuery);
}
