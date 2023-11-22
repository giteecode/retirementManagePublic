package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Dishes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageDishesByKeyQuery;
import com.ew.gerocomium.dao.vo.PageDishesByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜品表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface DishesMapper extends BaseMapper<Dishes> {
    /**
     * 根据搜索关键字查询菜品
     *
     * @param keyQuery
     * @return
     */
    List<PageDishesByKeyVo> listDishesByKey(@Param("keyQuery") PageDishesByKeyQuery keyQuery);

    /**
     * 根据老人编号获取该老人所选套餐的菜品列表
     *
     * @param elderId
     * @return
     */
    List<Dishes> listSetDishesByElderId(@Param("elderId") Long elderId);
}
