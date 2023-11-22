package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Accident;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageAccidentByKeyQuery;
import com.ew.gerocomium.dao.vo.GetAccidentByIdVo;
import com.ew.gerocomium.dao.vo.PageAccidentByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 事故登记表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface AccidentMapper extends BaseMapper<Accident> {
    /**
     * 根据关键字获取事故登记列表
     *
     * @param keyQuery
     * @return
     */
    List<PageAccidentByKeyVo> listAccidentByKeyVo(@Param("keyQuery") PageAccidentByKeyQuery keyQuery);

    /**
     * 根据编号获取事故登记
     *
     * @param accidenId
     * @return
     */
    GetAccidentByIdVo getAccidentById(@Param("accidentId") Long accidenId);
}
