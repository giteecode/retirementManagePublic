package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.po.OutboundMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetOutboundRecordByIdVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 出库物资表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface OutboundMaterialMapper extends BaseMapper<OutboundMaterial> {
    /**
     * 根据出库记录编号列表获取出库物资列表
     *
     * @param outboundRecordIdList
     * @return
     */
    List<DropDown> listOutboundMaterialByOutboundRecordIdList(@Param("outboundRecordIdList") List<Long> outboundRecordIdList);

    /**
     * 根据出库记录编号获取出库物资列表
     *
     * @param outboundRecordId
     * @return
     */
    List<GetOutboundRecordByIdVo.GetOutboundMaterialByIdVo> listOutboundMaterialByWarehouseRecordId(@Param("outboundRecordId") Long outboundRecordId);
}
