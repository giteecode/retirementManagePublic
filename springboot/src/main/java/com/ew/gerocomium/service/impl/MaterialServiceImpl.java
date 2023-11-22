package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.MaterialMapper;
import com.ew.gerocomium.dao.mapper.MaterialTypeMapper;
import com.ew.gerocomium.dao.po.Material;
import com.ew.gerocomium.dao.po.MaterialType;
import com.ew.gerocomium.dao.query.OperateMaterialQuery;
import com.ew.gerocomium.dao.query.OperateMaterialTypeQuery;
import com.ew.gerocomium.dao.query.PageMaterialByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateMaterialVo;
import com.ew.gerocomium.dao.vo.PageMaterialByKeyVo;
import com.ew.gerocomium.service.MaterialService;
import com.ew.gerocomium.service.common.MaterialFunc;
import com.ew.gerocomium.service.common.MaterialTypeFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Resource
    private MaterialTypeMapper materialTypeMapper;
    @Resource
    private MaterialTypeFunc materialTypeFunc;
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private MaterialFunc materialFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result getMaterialType(String materialTypeName) {
        return Result.success(BeanUtil.copyToList(materialTypeFunc.listNotDelMaerialType(materialTypeName), DropDown.class));
    }

    @Override
    public Result pageMaterialByKey(PageMaterialByKeyQuery query) {
        // 根据搜索关键字获取物资列表
        List<PageMaterialByKeyVo> pageMaterialByKeyVoList = materialMapper.listMaterialByKey(query);
        // 封装返回数据
        PageResult<PageMaterialByKeyVo> pageResult = pageUtil.packPageResultData(pageMaterialByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addMaterialType(OperateMaterialTypeQuery query) {
        // 验证分类名称是否已存在
        AssertUtil.isNull(materialTypeFunc.getMaterialTypeByName(query.getName()), ExceptionEnum.MATERIAL_TYPE_REPEAT);
        // 验证是否超过总数限制
        AssertUtil.notTrue(materialTypeFunc.checkTypeTotal(), ExceptionEnum.MATERIAL_TYPE_OUT);
        // 初始化物资分类
        query.setId(null);
        MaterialType materialType = BeanUtil.toBean(query, MaterialType.class);
        materialType.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        materialTypeMapper.insert(materialType);
        return Result.success();
    }

    @Override
    public Result getMaterialTypeById(Long materialTypeId) {
        // 根据编号获取物资分类
        MaterialType materialType = materialTypeMapper.selectById(materialTypeId);
        // 判断是否为空
        AssertUtil.notNull(materialType, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(materialType, DropDown.class));
    }

    @Override
    public Result editMaterialType(OperateMaterialTypeQuery query) {
        // 验证分类名称是否已存在
        MaterialType materialTypeByName = materialTypeFunc.getMaterialTypeByName(query.getName());
        boolean checkName = materialTypeByName != null && !Objects.equals(materialTypeByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.MATERIAL_TYPE_REPEAT);
        // 封装修改
        MaterialType materialType = BeanUtil.toBean(query, MaterialType.class);
        // 修改
        materialTypeMapper.updateById(materialType);
        return Result.success();
    }

    @Override
    public Result deleteMaterialType(Long materialTypeId) {
        // 验证物资分类下是否存在物资
        AssertUtil.notTrue(materialFunc.checkMaterialItem(materialTypeId), ExceptionEnum.MATERIAL_NOT_NULL);
        // 封装修改
        MaterialType materialType = new MaterialType();
        materialType.setId(materialTypeId);
        materialType.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        materialTypeMapper.updateById(materialType);
        return Result.success();
    }

    @Override
    public Result addMaterial(OperateMaterialQuery query) {
        // 验证物资名称是否已存在
        AssertUtil.isNull(materialFunc.getMaterialByName(query.getTypeId(), query.getName()), ExceptionEnum.MATERIAL_REPEAT);
        // 初始化物资
        query.setId(null);
        Material material = BeanUtil.toBean(query, Material.class);
        material.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        materialMapper.insert(material);
        return Result.success();
    }

    @Override
    public Result getMaterialById(Long materialId) {
        // 根据编号获取物资
        Material material = materialMapper.selectById(materialId);
        // 判断是否为空
        AssertUtil.notNull(material, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(BeanUtil.toBean(material, OperateMaterialVo.class));
    }

    @Override
    public Result editMaterial(OperateMaterialQuery query) {
        // 验证物资名称是否已存在
        Material materialByName = materialFunc.getMaterialByName(query.getTypeId(), query.getName());
        boolean checkName = materialByName != null && !Objects.equals(materialByName.getId(), query.getId());
        AssertUtil.notTrue(checkName, ExceptionEnum.MATERIAL_REPEAT);
        // 封装修改
        Material material = BeanUtil.toBean(query, Material.class);
        // 修改
        materialMapper.updateById(material);
        return Result.success();
    }

    @Override
    public Result deleteMaterial(Long materialId) {
        // 封装修改
        Material material = new Material();
        material.setId(materialId);
        material.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        materialMapper.updateById(material);
        return Result.success();
    }
}
