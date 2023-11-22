package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateServiceQuery;
import com.ew.gerocomium.dao.query.OperateServiceTypeQuery;
import com.ew.gerocomium.dao.query.PageServiceByKeyQuery;

public interface ServiceProjectService {
    /**
     * 获取服务类型
     *
     * @return
     */
    Result listServiceType(String serviceTypeName);

    /**
     * 分页查询服务
     *
     * @param pageServiceByKeyQuery
     * @return
     */
    Result pageServiceByKey(PageServiceByKeyQuery pageServiceByKeyQuery);

    /**
     * 新增服务类型
     *
     * @param operateServiceTypeQuery
     * @return
     */
    Result addServiceType(OperateServiceTypeQuery operateServiceTypeQuery);

    /**
     * 根据编号查询服务类型
     *
     * @param serviceTypeId
     * @return
     */
    Result getServiceTypeById(Long serviceTypeId);

    /**
     * 编辑服务类型
     *
     * @param operateServiceTypeQuery
     * @return
     */
    Result editServiceType(OperateServiceTypeQuery operateServiceTypeQuery);

    /**
     * 删除服务类型
     *
     * @param serviceTypeId
     * @return
     */
    Result deleteServiceType(Long serviceTypeId);

    /**
     * 新增服务
     *
     * @param operateServiceQuery
     * @return
     */
    Result addService(OperateServiceQuery operateServiceQuery);

    /**
     * 根据编号查询服务
     *
     * @param serviceId
     * @return
     */
    Result getServiceById(Long serviceId);

    /**
     * 编辑服务
     *
     * @param operateServiceQuery
     * @return
     */
    Result editService(OperateServiceQuery operateServiceQuery);

    /**
     * 删除服务
     *
     * @param serviceId
     * @return
     */
    Result deleteService(Long serviceId);
}
