package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ChargeEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.ServiceItemMapper;
import com.ew.gerocomium.dao.mapper.ServiceTypeMapper;
import com.ew.gerocomium.dao.po.ServiceItem;
import com.ew.gerocomium.dao.po.ServiceType;
import com.ew.gerocomium.dao.query.OperateServiceQuery;
import com.ew.gerocomium.dao.query.OperateServiceTypeQuery;
import com.ew.gerocomium.dao.query.PageServiceByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateServiceTypeVo;
import com.ew.gerocomium.dao.vo.OperateServiceVo;
import com.ew.gerocomium.dao.vo.PageServiceByKeyVo;
import com.ew.gerocomium.service.ServiceProjectService;
import com.ew.gerocomium.service.common.ServiceItemFunc;
import com.ew.gerocomium.service.common.ServiceTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceProjectServiceImpl implements ServiceProjectService {
    @Resource
    private ServiceTypeMapper serviceTypeMapper;
    @Resource
    private ServiceTypeFunc serviceTypeFunc;
    @Resource
    private ServiceItemMapper serviceItemMapper;
    @Resource
    private ServiceItemFunc serviceItemFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result listServiceType(String serviceTypeName) {
        return Result.success(BeanUtil.copyToList(serviceTypeFunc.listNotDelServiceType(serviceTypeName), DropDown.class));
    }

    @Override
    public Result pageServiceByKey(PageServiceByKeyQuery query) {
        // 根据搜索关键字查询服务项目
        List<ServiceItem> listNotDelServiceItem = serviceItemFunc.listNotDelServiceItemByKey(query.getName(), query.getTypeId(), ChargeEnum.ALL.getMethod());
        // 实体转换
        List<PageServiceByKeyVo> pageServiceByKeyVoList = BeanUtil.copyToList(listNotDelServiceItem, PageServiceByKeyVo.class);
        // 封装返回数据
        PageResult<PageServiceByKeyVo> pageResult = pageUtil.packPageResultData(pageServiceByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addServiceType(OperateServiceTypeQuery query) {
        // 验证服务类型名称是否重复
        AssertUtil.isNull(serviceTypeFunc.getServiceTypeByName(query.getName()), ExceptionEnum.SERVICE_TYPE_REPEAT);
        // 验证总数是否超出限制
        AssertUtil.notTrue(serviceTypeFunc.checkTypeTotal(), ExceptionEnum.SERVICE_TYPE_OUT);
        // 初始化服务类型
        query.setId(null);
        ServiceType serviceType = BeanUtil.toBean(query, ServiceType.class);
        serviceType.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        serviceTypeMapper.insert(serviceType);
        return Result.success();
    }

    @Override
    public Result getServiceTypeById(Long serviceTypeId) {
        // 根据编号获取服务项目分类
        ServiceType serviceType = serviceTypeMapper.selectById(serviceTypeId);
        // 判断是否为空
        AssertUtil.notNull(serviceType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(serviceType, OperateServiceTypeVo.class));
    }

    @Override
    public Result editServiceType(OperateServiceTypeQuery query) {
        // 验证服务类型名称是否重复
        ServiceType serviceTypeByName = serviceTypeFunc.getServiceTypeByName(query.getName());
        boolean checkName = serviceTypeByName != null && !Objects.equals(serviceTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.SERVICE_TYPE_REPEAT);
        // 封装修改
        ServiceType serviceType = BeanUtil.toBean(query, ServiceType.class);
        // 修改
        serviceTypeMapper.updateById(serviceType);
        return Result.success();
    }

    @Override
    public Result deleteServiceType(Long serviceTypeId) {
        // 判断该分类下是否存在子级服务
        AssertUtil.notTrue(serviceItemFunc.checkServiceItem(serviceTypeId), ExceptionEnum.SERVICE_NOT_NULL);
        // 封装修改
        ServiceType serviceType = new ServiceType();
        serviceType.setId(serviceTypeId);
        serviceType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        serviceTypeMapper.updateById(serviceType);
        return Result.success();
    }

    @Override
    public Result addService(OperateServiceQuery query) {
        // 判断服务是否存在
        AssertUtil.isNull(serviceItemFunc.getServiceItemByName(query.getName(), query.getTypeId()), ExceptionEnum.SERVICE_REPEAT);
        // 判断服务总数是否超过总数限制
        AssertUtil.notTrue(serviceItemFunc.checkServiceTotal(query.getTypeId()), ExceptionEnum.SERVICE_OUT);
        // 初始化服务
        query.setId(null);
        ServiceItem serviceItem = BeanUtil.toBean(query, ServiceItem.class);
        serviceItem.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        serviceItemMapper.insert(serviceItem);
        return Result.success();
    }

    @Override
    public Result getServiceById(Long serviceId) {
        // 根据编号获取服务项目
        ServiceItem serviceItem = serviceItemMapper.selectById(serviceId);
        // 判断是否为空
        AssertUtil.notNull(serviceItem, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(serviceItem, OperateServiceVo.class));
    }

    @Override
    public Result editService(OperateServiceQuery query) {
        // 判断服务是否存在
        ServiceItem serviceItemByName = serviceItemFunc.getServiceItemByName(query.getName(), query.getTypeId());
        boolean checkName = serviceItemByName != null && !Objects.equals(serviceItemByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.SERVICE_REPEAT);
        // 封装修改
        ServiceItem serviceItem = BeanUtil.toBean(query, ServiceItem.class);
        // 修改
        serviceItemMapper.updateById(serviceItem);
        return Result.success();
    }

    @Override
    public Result deleteService(Long serviceId) {
        // 封装修改
        ServiceItem serviceItem = new ServiceItem();
        serviceItem.setId(serviceId);
        serviceItem.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        serviceItemMapper.updateById(serviceItem);
        return Result.success();
    }
}
