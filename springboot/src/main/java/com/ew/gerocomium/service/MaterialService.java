package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateMaterialQuery;
import com.ew.gerocomium.dao.query.OperateMaterialTypeQuery;
import com.ew.gerocomium.dao.query.PageMaterialByKeyQuery;

public interface MaterialService {
    /**
     * 获取物资分类
     *
     * @param materialTypeName
     * @return
     */
    Result getMaterialType(String materialTypeName);

    /**
     * 分页查询物资
     *
     * @param pageMaterialByKeyQuery
     * @return
     */
    Result pageMaterialByKey(PageMaterialByKeyQuery pageMaterialByKeyQuery);

    /**
     * 新增物资分类
     *
     * @param operateMaterialTypeQuery
     * @return
     */
    Result addMaterialType(OperateMaterialTypeQuery operateMaterialTypeQuery);

    /**
     * 根据编号查询物资分类
     *
     * @param materialTypeId
     * @return
     */
    Result getMaterialTypeById(Long materialTypeId);

    /**
     * 编辑物资分类
     *
     * @param operateMaterialTypeQuery
     * @return
     */
    Result editMaterialType(OperateMaterialTypeQuery operateMaterialTypeQuery);

    /**
     * 删除物资分类
     *
     * @param materialTypeId
     * @return
     */
    Result deleteMaterialType(Long materialTypeId);

    /**
     * 新增物资
     *
     * @param operateMaterialQuery
     * @return
     */
    Result addMaterial(OperateMaterialQuery operateMaterialQuery);

    /**
     * 根据编号查询物资
     *
     * @param materialId
     * @return
     */
    Result getMaterialById(Long materialId);

    /**
     * 编辑物资
     *
     * @param operateMaterialQuery
     * @return
     */
    Result editMaterial(OperateMaterialQuery operateMaterialQuery);

    /**
     * 删除物资
     *
     * @param materialId
     * @return
     */
    Result deleteMaterial(Long materialId);
}
