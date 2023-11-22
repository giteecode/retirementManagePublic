package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.MaterialTypeMapper;
import com.ew.gerocomium.dao.po.MaterialType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物资分类表公共方法
 */
@Component
public class MaterialTypeFunc {
    @Resource
    private MaterialTypeMapper materialTypeMapper;

    /**
     * 获取未被删除的物资分类
     *
     * @param materialTypeName
     * @return
     */
    public List<MaterialType> listNotDelMaerialType(String materialTypeName) {
        return materialTypeMapper.selectList(new LambdaQueryWrapper<MaterialType>()
                .and(materialTypeLambdaQueryWrapper -> {
                    materialTypeLambdaQueryWrapper
                            .eq(MaterialType::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(materialTypeName)) {
                        materialTypeLambdaQueryWrapper
                                .like(MaterialType::getName, materialTypeName);
                    }
                })
        );
    }

    /**
     * 根据物资分类名称获取物资分类
     *
     * @param typeName
     * @return
     */
    public MaterialType getMaterialTypeByName(String typeName) {
        return materialTypeMapper.selectOne(new LambdaQueryWrapper<MaterialType>()
                .eq(MaterialType::getName, typeName)
                .eq(MaterialType::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 判断分类是否超过总数限制
     *
     * @return
     */
    public Boolean checkTypeTotal() {
        Long typeTotal = materialTypeMapper.selectCount(new LambdaQueryWrapper<MaterialType>()
                .eq(MaterialType::getDelFlag, YesNoEnum.NO.getCode()));
        return typeTotal >= Constant.TOTAL_LIMIT;
    }
}
