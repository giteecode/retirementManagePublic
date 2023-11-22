package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.AddNurseReserveQuery;
import com.ew.gerocomium.dao.query.ExecuteNurseReserveQuery;
import com.ew.gerocomium.dao.query.PageNurseReserveByKeyQuery;

public interface NurseReserveService {
    /**
     * 分页查询护理预定
     *
     * @param PageNurseReserveByKeyQuery
     * @return
     */
    Result pageNurseReserveByKey(PageNurseReserveByKeyQuery PageNurseReserveByKeyQuery);

    /**
     * 获取服务项目
     *
     * @return
     */
    Result listService();

    /**
     * 新增护理预定
     *
     * @param addNurseReserveQuery
     * @return
     */
    Result addNurseReserve(AddNurseReserveQuery addNurseReserveQuery);

    /**
     * 护理人员
     *
     * @return
     */
    Result listNurseStaff();

    /**
     * 执行护理预定
     *
     * @param executeNurseReserve
     * @return
     */
    Result executeNurseReserve(ExecuteNurseReserveQuery executeNurseReserve);
}
