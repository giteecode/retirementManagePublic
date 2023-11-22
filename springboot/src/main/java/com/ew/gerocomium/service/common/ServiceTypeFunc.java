package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.ServiceTypeMapper;
import com.ew.gerocomium.dao.po.LabelType;
import com.ew.gerocomium.dao.po.ServiceType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务分类表公共方法
 */
@Component
public class ServiceTypeFunc {
    @Resource
    private ServiceTypeMapper serviceTypeMapper;

    /**
     * 获取未被删除的服务类型
     *
     * @return
     */
    public List<ServiceType> listNotDelServiceType(String serviceTypeName) {
        return serviceTypeMapper.selectList(new LambdaQueryWrapper<ServiceType>()
                .and(serviceTypeLambdaQueryWrapper -> {
                    serviceTypeLambdaQueryWrapper
                            .eq(ServiceType::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(serviceTypeName)) {
                        serviceTypeLambdaQueryWrapper
                                .like(ServiceType::getName, serviceTypeName);
                    }
                })
        );
    }

    /**
     * 根据服务类型名称获取服务类型
     *
     * @return
     */
    public ServiceType getServiceTypeByName(String typeName) {
        return serviceTypeMapper.selectOne(new LambdaQueryWrapper<ServiceType>()
                .eq(ServiceType::getName, typeName)
                .eq(ServiceType::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断分类是否超过总数限制
     *
     * @return
     */
    public Boolean checkTypeTotal() {
        Long typeTotal = serviceTypeMapper.selectCount(new LambdaQueryWrapper<ServiceType>()
                .eq(ServiceType::getDelFlag, YesNoEnum.NO.getCode()));
        return typeTotal >= Constant.TOTAL_LIMIT;
    }
}
