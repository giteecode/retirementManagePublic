package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.RetreatApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageRetreatApplyQuery;
import com.ew.gerocomium.dao.query.PageRetreatAuditQuery;
import com.ew.gerocomium.dao.vo.PageRetreatByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 退住申请表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface RetreatApplyMapper extends BaseMapper<RetreatApply> {
    /**
     * 根据搜索关键字查询退住申请信息
     *
     * @param keyQuery
     * @return
     */
    List<PageRetreatByKeyVo> listRetreatApplyByKey(@Param("keyQuery") PageRetreatApplyQuery keyQuery);

    /**
     * 根据搜索关键字查询退住申请信息
     *
     * @param keyQuery
     * @return
     */
    List<PageRetreatByKeyVo> listRetreatAuditByKey(@Param("keyQuery") PageRetreatAuditQuery keyQuery);
}
