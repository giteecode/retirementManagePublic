package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.OutboundMaterialMapper;
import com.ew.gerocomium.dao.mapper.WarehouseMaterialMapper;
import com.ew.gerocomium.dao.po.OutboundMaterial;
import com.ew.gerocomium.dao.po.WarehouseMaterial;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OutboundMaterialFunc extends ServiceImpl<OutboundMaterialMapper, OutboundMaterial> {
    @Resource
    private OutboundMaterialMapper outboundMaterialMapper;
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private WarehouseMaterialFunc warehouseMaterialFunc;

    /**
     * 回退该出库记录所有物资
     *
     * @param outboundRecordId
     */
    public void materialRollback(Long outboundRecordId) {
        // 根据编号获取出库物资列表
        List<OutboundMaterial> outboundMaterialList = outboundMaterialMapper.selectList(
                new LambdaQueryWrapper<OutboundMaterial>()
                        .eq(OutboundMaterial::getOutboundRecordId, outboundRecordId)
        );
        // 获取入库物资编号列表
        List<Long> warehouseMaterialIdList = new ArrayList<>();
        outboundMaterialList.forEach(outboundMaterial -> warehouseMaterialIdList.add(outboundMaterial.getWarehouseMaterialId()));
        // 根据入库物资编号列表获取入库物资列表
        List<WarehouseMaterial> warehouseMaterialList = warehouseMaterialMapper.selectBatchIds(warehouseMaterialIdList);
        // 封装修改库存量
        warehouseMaterialList.forEach(warehouseMaterial -> {
            OutboundMaterial outboundMaterial = outboundMaterialList.stream()
                    .filter(material ->
                            Objects.equals(warehouseMaterial.getId(), material.getWarehouseMaterialId())
                    ).collect(Collectors.toList())
                    .get(0);
            warehouseMaterial.setInventory(warehouseMaterial.getInventory() + outboundMaterial.getOutboundNum());
        });
        // 修改库存量
        warehouseMaterialFunc.updateBatchById(warehouseMaterialList);
    }
}
