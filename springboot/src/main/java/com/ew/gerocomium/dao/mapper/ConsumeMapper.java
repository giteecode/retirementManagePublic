package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Consume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageConsumeByKeyQuery;
import com.ew.gerocomium.dao.vo.PageConsumeByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消费记录表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface ConsumeMapper extends BaseMapper<Consume> {
    /**
     * 根据搜索关键字获取消费记录
     *
     * @param elderName
     * @param startTime
     * @param endTime
     * @return
     */
    List<PageConsumeByKeyVo> listConsumeByKey(@Param("elderName") String elderName,
                                              @Param("startTime") Date startTime,
                                              @Param("endTime") Date endTime);
}
