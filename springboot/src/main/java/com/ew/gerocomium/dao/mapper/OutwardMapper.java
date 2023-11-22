package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Outward;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageOutwardByKeyQuery;
import com.ew.gerocomium.dao.vo.GetOutwardByIdVo;
import com.ew.gerocomium.dao.vo.PageOutwardByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 外出登记表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface OutwardMapper extends BaseMapper<Outward> {
    /**
     * 根据关键字获取外出登记
     *
     * @param keyQuery
     * @param startTime
     * @param endTime
     * @return
     */
    List<PageOutwardByKeyVo> listOutwardByKey(@Param("keyQuery") PageOutwardByKeyQuery keyQuery,
                                              @Param("startTime") Date startTime,
                                              @Param("endTime") Date endTime);

    /**
     * 根据编号获取外出登记
     *
     * @param outwardId
     * @return
     */
    GetOutwardByIdVo getOutwardById(@Param("outwardId") Long outwardId);
}
