package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.ActiveParticipant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetActiveByIdVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动参与者表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface ActiveParticipantMapper extends BaseMapper<ActiveParticipant> {
    /**
     * 根据活动编号获取活动参与老人列表
     *
     * @param activeId
     * @return
     */
    List<GetActiveByIdVo.ParticipateElderVo> listParticipateElder(@Param("activeId") Long activeId);
}
