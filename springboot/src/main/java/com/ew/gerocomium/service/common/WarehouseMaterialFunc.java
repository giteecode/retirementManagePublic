package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.WarehouseMaterialMapper;
import com.ew.gerocomium.dao.po.WarehouseMaterial;
import org.springframework.stereotype.Component;

/**
 * 入库物资表公共方法
 */
@Component
public class WarehouseMaterialFunc extends ServiceImpl<WarehouseMaterialMapper, WarehouseMaterial> {
}
