package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Visit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageVisitByKeyQuery;
import com.ew.gerocomium.dao.vo.GetVisitByIdVo;
import com.ew.gerocomium.dao.vo.PageVisitByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 来访登记表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface VisitMapper extends BaseMapper<Visit> {
    /**
     * 根据关键字获取来访登记
     *
     * @param keyQuery
     * @return
     */
    List<PageVisitByKeyVo> listVisitByKey(@Param("keyQuery") PageVisitByKeyQuery keyQuery);

    /**
     * 根据编号获取来访登记
     *
     * @param visitId
     * @return
     */
    GetVisitByIdVo getVisitById(@Param("visitId") Long visitId);
}
