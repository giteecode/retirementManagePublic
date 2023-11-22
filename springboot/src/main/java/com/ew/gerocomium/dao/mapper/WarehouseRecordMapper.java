package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.WarehouseRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageWarehouseRecordByKeyQuery;
import com.ew.gerocomium.dao.vo.GetWarehouseRecordByIdVo;
import com.ew.gerocomium.dao.vo.PageWarehouseRecordByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 入库登记表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface WarehouseRecordMapper extends BaseMapper<WarehouseRecord> {
    /**
     * 根据搜索关键字查询入库记录
     *
     * @param keyQuery
     * @param startTime
     * @param endTime
     * @return
     */
    List<PageWarehouseRecordByKeyVo> listWarehouseRecordByKey(@Param("keyQuery") PageWarehouseRecordByKeyQuery keyQuery,
                                                              @Param("startTime") Date startTime,
                                                              @Param("endTime") Date endTime);

    /**
     * 根据编号查询入库记录
     *
     * @param warehouseRecordId
     * @return
     */
    GetWarehouseRecordByIdVo getWarehouseRecordById(@Param("warehouseRecordId") Long warehouseRecordId);
}
