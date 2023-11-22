package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.NurseReserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageNurseReserveByKeyQuery;
import com.ew.gerocomium.dao.vo.PageNurseReserveByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 护理预定表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface NurseReserveMapper extends BaseMapper<NurseReserve> {
    /**
     * 根据关键词获取护理预定列表
     *
     * @param keyQuery
     * @return
     */
    List<PageNurseReserveByKeyVo> listNurseReserveByKey(@Param("keyQuery") PageNurseReserveByKeyQuery keyQuery);
}
