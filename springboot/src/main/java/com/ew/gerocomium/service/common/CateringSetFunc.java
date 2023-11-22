package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.CateringSetMapper;
import com.ew.gerocomium.dao.mapper.SetDishesMapper;
import com.ew.gerocomium.dao.po.CateringSet;
import com.ew.gerocomium.dao.vo.GetCateringSetByIdVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 餐饮套餐表公共方法
 */
@Component
public class CateringSetFunc {
    @Resource
    private CateringSetMapper cateringSetMapper;
    @Resource
    private SetDishesMapper setDishesMapper;

    /**
     * 获取未被删除的餐饮套餐
     *
     * @return
     */
    public List<CateringSet> listNotDelCateringSet() {
        return cateringSetMapper.selectList(
                new LambdaQueryWrapper<CateringSet>()
                        .eq(CateringSet::getDelFlag, YesNoEnum.NO.getCode())
        );
    }

    /**
     * 获取未被删除的餐饮套餐
     *
     * @return
     */
    public List<CateringSet> listNotDelCateringSet(String setName) {
        return cateringSetMapper.selectList(new LambdaQueryWrapper<CateringSet>()
                .and(cateringSetLambdaQueryWrapper -> {
                    cateringSetLambdaQueryWrapper
                            .eq(CateringSet::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(setName)) {
                        cateringSetLambdaQueryWrapper
                                .like(CateringSet::getName, setName);
                    }
                })
        );
    }

    /**
     * 根据编号获取餐饮套餐
     *
     * @param setId
     * @return
     */
    public GetCateringSetByIdVo getCateringSetById(Long setId) {
        // 根据编号获取餐饮套餐
        CateringSet cateringSet = cateringSetMapper.selectById(setId);
        // 判断是否为空
        AssertUtil.notNull(cateringSet, ExceptionEnum.DATA_NOT_EXIST);
        // 转换实体
        GetCateringSetByIdVo getCateringSetByIdVo = BeanUtil.toBean(cateringSet, GetCateringSetByIdVo.class);
        // 根据套餐编号获取食物信息并设值
        getCateringSetByIdVo.setSetDishesVoList(setDishesMapper.listSetDishesBySetId(setId));
        return getCateringSetByIdVo;
    }

    /**
     * 根据套餐名称获取套餐信息
     *
     * @param setName
     * @return
     */
    public CateringSet getCateringSetByName(String setName) {
        return cateringSetMapper.selectOne(new LambdaQueryWrapper<CateringSet>()
                .eq(CateringSet::getName, setName)
                .eq(CateringSet::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
