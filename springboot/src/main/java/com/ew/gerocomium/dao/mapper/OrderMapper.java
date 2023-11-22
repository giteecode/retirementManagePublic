package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageOrderByKeyQuery;
import com.ew.gerocomium.dao.vo.GetOrderByIdVo;
import com.ew.gerocomium.dao.vo.PageOrderByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订餐表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 根据关键字查询点餐列表
     *
     * @param keyQuery
     * @return
     */
    List<PageOrderByKeyVo> listOrderByKey(@Param("keyQuery") PageOrderByKeyQuery keyQuery);

    /**
     * 根据订单编号获取信息
     *
     * @param orderId
     * @return
     */
    GetOrderByIdVo getOrderById(@Param("orderId") Long orderId);
}
