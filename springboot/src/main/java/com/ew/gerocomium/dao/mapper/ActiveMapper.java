package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Active;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageActiveByKeyQuery;
import com.ew.gerocomium.dao.vo.PageActiveByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface ActiveMapper extends BaseMapper<Active> {
    /**
     * 根据关键词查询活动
     *
     * @param keyQuery
     * @param startTime
     * @param endTime
     * @return
     */
    List<PageActiveByKeyVo> listActiveByKey(@Param("keyQuery") PageActiveByKeyQuery keyQuery,
                                            @Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime);
}
