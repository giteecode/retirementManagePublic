package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageDepositRechargeByKeyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;
import com.ew.gerocomium.dao.query.RechargeQuery;

public interface DepositRechargeService {
    /**
     * 分页查询预存充值
     *
     * @param pageDepositRechargeByKeyQuery
     * @return
     */
    Result pageDepositRechargeByKey(PageDepositRechargeByKeyQuery pageDepositRechargeByKeyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 入住老人账户充值
     *
     * @param rechargeQuery
     * @return
     */
    Result recharge(RechargeQuery rechargeQuery);
}
