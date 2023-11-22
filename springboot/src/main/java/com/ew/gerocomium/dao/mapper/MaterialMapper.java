package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageMaterialByKeyQuery;
import com.ew.gerocomium.dao.vo.PageMaterialByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物资表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface MaterialMapper extends BaseMapper<Material> {
    /**
     * 根据搜索关键字获取物资列表
     *
     * @param keyQuery
     * @return
     */
    List<PageMaterialByKeyVo> listMaterialByKey(@Param("keyQuery") PageMaterialByKeyQuery keyQuery);
}
