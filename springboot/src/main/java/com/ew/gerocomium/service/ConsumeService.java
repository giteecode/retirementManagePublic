package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageConsumeByKeyQuery;

public interface ConsumeService {
    /**
     * 分页查询消费记录
     *
     * @param pageConsumeByKeyQuery
     * @return
     */
    Result pageConsumeByKey(PageConsumeByKeyQuery pageConsumeByKeyQuery);
}
