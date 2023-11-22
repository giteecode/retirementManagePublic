package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.OutboundRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageOutboundRecordByKeyQuery;
import com.ew.gerocomium.dao.vo.GetOutboundRecordByIdVo;
import com.ew.gerocomium.dao.vo.PageOutboundRecordByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 出库登记表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface OutboundRecordMapper extends BaseMapper<OutboundRecord> {
    /**
     * 根据搜索关键字查询出库记录
     *
     * @param keyQuery
     * @param startTime
     * @param endTime
     * @return
     */
    List<PageOutboundRecordByKeyVo> listOutboundRecordByKey(@Param("keyQuery") PageOutboundRecordByKeyQuery keyQuery,
                                                            @Param("startTime") Date startTime,
                                                            @Param("endTime") Date endTime);

    /**
     * 根据编号获取出库记录信息
     *
     * @param outboundRecordId
     * @return
     */
    GetOutboundRecordByIdVo getOutboundRecordById(@Param("outboundRecordId") Long outboundRecordId);
}
