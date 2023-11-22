package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.SetDishes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetCateringSetByIdVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 套餐食物表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface SetDishesMapper extends BaseMapper<SetDishes> {
    /**
     * 根据套餐编号获取食物信息并设值
     *
     * @param setId
     * @return
     */
    List<GetCateringSetByIdVo.SetDishesVo> listSetDishesBySetId(@Param("setId") Long setId);
}
