package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.AddOrderQuery;
import com.ew.gerocomium.dao.query.PageOrderByKeyQuery;
import com.ew.gerocomium.dao.query.SendOrderQuery;

public interface OrderService {
    /**
     * 分页查询点餐
     *
     * @param pageOrderByKeyQuery
     * @return
     */
    Result pageOrderByKey(PageOrderByKeyQuery pageOrderByKeyQuery);

    /**
     * 新增点餐
     *
     * @param addOrderQuery
     * @return
     */
    Result addOrder(AddOrderQuery addOrderQuery);

    /**
     * 根据编号获取点餐
     *
     * @param orderId
     * @return
     */
    Result getOrderById(Long orderId);

    /**
     * 送餐
     *
     * @param sendOrderQuery
     * @return
     */
    Result sendOrder(SendOrderQuery sendOrderQuery);
}
