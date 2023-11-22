package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateCheckContractQuery;
import com.ew.gerocomium.dao.query.PageCheckContractByKeyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;

public interface CheckContractService {
    /**
     * 分页查询入住签约
     *
     * @param pageCheckContractByKeyQuery
     * @return
     */
    Result pageCheckContractByKey(PageCheckContractByKeyQuery pageCheckContractByKeyQuery);

    /**
     * 分页搜索老人
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 获取护理等级列表
     *
     * @return
     */
    Result listNurseGrade();

    /**
     * 获取餐饮套餐列表
     *
     * @return
     */
    Result listCateringSet();

    /**
     * 获取楼栋树
     * @return
     */
    Result getBuildTree();

    /**
     * 根据编号查询床位
     *
     * @param bedId
     * @return
     */
    Result getBedById(Long bedId);

    /**
     * 新增入住签约
     *
     * @param operateCheckContractQuery
     * @return
     */
    Result addCheckContract(OperateCheckContractQuery operateCheckContractQuery);

    /**
     * 根据老人编号查询入住签约
     *
     * @param elderId
     * @return
     */
    Result getCheckContractById(Long elderId);

    /**
     * 编辑入住签约
     *
     * @param operateCheckContractQuery
     * @return
     */
    Result editCheckContract(OperateCheckContractQuery operateCheckContractQuery);

    /**
     * 删除入住签约
     *
     * @param elderId
     * @return
     */
    Result deleteCheckContract(Long elderId);
}
