package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.ChargeEnum;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.ServiceItemMapper;
import com.ew.gerocomium.dao.po.ServiceItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 服务项目表公共方法
 */
@Component
public class ServiceItemFunc {
    @Resource
    private ServiceItemMapper serviceItemMapper;

    /**
     * 根据搜索关键字查询服务项目
     *
     * @param serviceName
     * @param typeId
     * @param chargeMethod
     * @return
     */
    public List<ServiceItem> listNotDelServiceItemByKey(String serviceName, Long typeId, String chargeMethod) {
        return serviceItemMapper.selectList(new LambdaQueryWrapper<ServiceItem>()
                .and(serviceItemLambdaQueryWrapper -> {
                    if (Objects.equals(chargeMethod, ChargeEnum.ONCE.getMethod())) {
                        serviceItemLambdaQueryWrapper
                                .eq(ServiceItem::getChargeMethod, ChargeEnum.ONCE.getMethod());
                    } else if (Objects.equals(chargeMethod, ChargeEnum.MONTH.getMethod())) {
                        serviceItemLambdaQueryWrapper
                                .eq(ServiceItem::getChargeMethod, ChargeEnum.MONTH.getMethod());
                    } else {
                        serviceItemLambdaQueryWrapper
                                .eq(ServiceItem::getChargeMethod, ChargeEnum.ONCE.getMethod()).or()
                                .eq(ServiceItem::getChargeMethod, ChargeEnum.MONTH.getMethod());
                    }
                })
                .and(serviceItemLambdaQueryWrapper -> {
                    serviceItemLambdaQueryWrapper
                            .eq(ServiceItem::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(serviceName)) {
                        serviceItemLambdaQueryWrapper
                                .like(ServiceItem::getName, serviceName);
                    }
                    if (ObjUtil.isNotEmpty(typeId)) {
                        serviceItemLambdaQueryWrapper
                                .eq(ServiceItem::getTypeId, typeId);
                    }
                })
                .orderByDesc(ServiceItem::getUpdateTime)
        );
    }

    /**
     * 判断该分类下是否存在子级服务
     *
     * @param typeId
     * @return
     */
    public Boolean checkServiceItem(Long typeId) {
        List<ServiceItem> serviceItemList = serviceItemMapper.selectList(new LambdaQueryWrapper<ServiceItem>()
                .eq(ServiceItem::getTypeId, typeId)
                .eq(ServiceItem::getDelFlag, YesNoEnum.NO.getCode()));
        return serviceItemList.size() > 0;
    }

    /**
     * 根据服务名称查询服务
     *
     * @param serviceName
     * @param typeId
     * @return
     */
    public ServiceItem getServiceItemByName(String serviceName, Long typeId) {
        return serviceItemMapper.selectOne(new LambdaQueryWrapper<ServiceItem>()
                .eq(ServiceItem::getName, serviceName)
                .eq(ServiceItem::getTypeId, typeId)
                .eq(ServiceItem::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断服务是否超过总数限制
     *
     * @param typeId
     * @return
     */
    public Boolean checkServiceTotal(Long typeId) {
        Long serviceTotal = serviceItemMapper.selectCount(new LambdaQueryWrapper<ServiceItem>()
                .eq(ServiceItem::getTypeId, typeId)
                .eq(ServiceItem::getDelFlag, YesNoEnum.NO.getCode()));
        return serviceTotal >= Constant.TOTAL_LIMIT;
    }
}
