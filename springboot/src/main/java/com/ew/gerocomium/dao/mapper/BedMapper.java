package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Bed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetBedByIdVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 床位表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface BedMapper extends BaseMapper<Bed> {
    /**
     * 根据编号获取床位信息
     *
     * @param bedId
     * @return
     */
    GetBedByIdVo getBedById(@Param("bedId") Long bedId);
}
