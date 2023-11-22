package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Reserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.GetReserveByReserveIdAndElderIdQuery;
import com.ew.gerocomium.dao.query.PageReserveByKeyQuery;
import com.ew.gerocomium.dao.vo.GetReserveByReserveIdAndElderIdVo;
import com.ew.gerocomium.dao.vo.PageReserveByKeyVo;
import com.ew.gerocomium.dao.vo.ExpireReserveVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 预定表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface ReserveMapper extends BaseMapper<Reserve> {
    /**
     * 根据搜索关键字查询预定信息
     *
     * @param keyQuery
     * @return
     */
    List<PageReserveByKeyVo> listReserveByKey(@Param("keyQuery") PageReserveByKeyQuery keyQuery);

    /**
     * 根据预定编号和老人编号获取预定信息
     *
     * @param idQuery
     * @return
     */
    GetReserveByReserveIdAndElderIdVo getReserveByReserveIdAndElderId(@Param("idQuery") GetReserveByReserveIdAndElderIdQuery idQuery);

    /**
     * 获取过期预定列表
     *
     * @param expireTime
     * @return
     */
    List<ExpireReserveVo> listExpireReserve(@Param("expireTime") Date expireTime);
}
