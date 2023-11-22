package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.OrderDishesMapper;
import com.ew.gerocomium.dao.po.OrderDishes;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单菜品表公共方法
 */
@Component
public class OrderDishesFunc extends ServiceImpl<OrderDishesMapper, OrderDishes> {
    @Resource
    private OrderDishesMapper orderDishesMapper;

    /**
     * 根据订单编号获取订单菜品列表
     *
     * @param orderId
     * @return
     */
    public List<OrderDishes> listOrderDishesByOrderId(Long orderId) {
        return orderDishesMapper.selectList(new LambdaQueryWrapper<OrderDishes>()
                .eq(OrderDishes::getOrderId, orderId));
    }
}
