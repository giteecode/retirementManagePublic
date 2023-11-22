package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageWarehouseByKeyQuery;
import com.ew.gerocomium.dao.vo.PageWarehouseByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 仓库表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {
    /**
     * 根据关键字获取仓库列表
     *
     * @param keyQuery
     * @return
     */
    List<PageWarehouseByKeyVo> listWarehouseByKey(@Param("keyQuery") PageWarehouseByKeyQuery keyQuery);
}
