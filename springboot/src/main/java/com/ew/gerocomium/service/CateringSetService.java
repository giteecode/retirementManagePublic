package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateCateringSetQuery;
import com.ew.gerocomium.dao.query.PageCateringSetByKeyQuery;

public interface CateringSetService {
    /**
     * 分页查询餐饮套餐
     *
     * @param pageCateringSetByKeyQuery
     * @return
     */
    Result pageCateringSetByKey(PageCateringSetByKeyQuery pageCateringSetByKeyQuery);

    /**
     * 新增餐饮套餐
     *
     * @param operateCateringSetQuery
     * @return
     */
    Result addCateringSet(OperateCateringSetQuery operateCateringSetQuery);

    /**
     * 根据编号查询餐饮套餐
     *
     * @param setId
     * @return
     */
    Result getCateringSetById(Long setId);

    /**
     * 编辑餐饮套餐
     *
     * @param operateCateringSetQuery
     * @return
     */
    Result editCateringSet(OperateCateringSetQuery operateCateringSetQuery);

    /**
     * 删除餐饮套餐
     *
     * @param setId
     * @return
     */
    Result deleteCateringSet(Long setId);
}
