package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.ConsumeEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.DishesMapper;
import com.ew.gerocomium.dao.mapper.OrderMapper;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.AddOrderQuery;
import com.ew.gerocomium.dao.query.PageOrderByKeyQuery;
import com.ew.gerocomium.dao.query.SendOrderQuery;
import com.ew.gerocomium.dao.vo.GetOrderByIdVo;
import com.ew.gerocomium.dao.vo.PageOrderByKeyVo;
import com.ew.gerocomium.service.OrderService;
import com.ew.gerocomium.service.common.ConsumeFunc;
import com.ew.gerocomium.service.common.ElderFunc;
import com.ew.gerocomium.service.common.OrderDishesFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDishesFunc orderDishesFunc;
    @Resource
    private DishesMapper dishesMapper;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private ConsumeFunc consumeFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageOrderByKey(PageOrderByKeyQuery query) {
        // 根据关键字查询点餐列表
        List<PageOrderByKeyVo> pageOrderByKeyVoList = orderMapper.listOrderByKey(query);
        // 封装返回数据
        PageResult<PageOrderByKeyVo> pageResult = pageUtil.packPageResultData(pageOrderByKeyVoList, query.getPageNum(), query.getPageSize());
        // 格式化日期
        pageResult.getList().forEach(pageOrderByKeyVo -> {
            pageOrderByKeyVo.setDineDate(pageOrderByKeyVo.getDineDate().substring(0, 10));
            pageOrderByKeyVo.setDeliverDishesDate(
                    ObjUtil.isNotEmpty(pageOrderByKeyVo.getDeliverDishesDate()) ?
                            pageOrderByKeyVo.getDeliverDishesDate().substring(0, 10) :
                            ""
            );
        });
        // 替换订单状态
        pageResult.getList().forEach(pageOrderByKeyVo -> pageOrderByKeyVo.setOrderFlag(
                Objects.equals(pageOrderByKeyVo.getOrderFlag(), YesNoEnum.NO.getCode()) ?
                        "待支付" :
                        "已完成"
        ));
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addOrder(AddOrderQuery query) {
        // 根据老人编号获取该老人所选套餐的菜品列表
        List<Dishes> elderSetDishesList = dishesMapper.listSetDishesByElderId(query.getElderId());
        // 对所点菜品根据菜品编号进行分组
        Map<Long, List<AddOrderQuery.AddOrderDishesQuery>> dishesNumMap = query.getOrderDishesList()
                .parallelStream()
                .collect(Collectors.groupingBy(AddOrderQuery.AddOrderDishesQuery::getDishesId));
        // 封装所点菜品编号列表
        List<Long> dishesIdList = dishesNumMap.keySet()
                .parallelStream()
                .collect(Collectors.toList());
        // 根据编号列表获取菜品列表
        List<Dishes> dishesList = dishesMapper.selectBatchIds(dishesIdList);
        // 实例化支付总额
        BigDecimal payAmount = new BigDecimal("0");
        // 实例化订单菜品列表
        List<OrderDishes> orderDishesList = new ArrayList<>();
        for (Long dishesId : dishesNumMap.keySet()) {
            // 获取当前菜品信息
            Dishes dishes = dishesList.stream()
                    .filter(dishes0 -> Objects.equals(dishes0.getId(), dishesId))
                    .collect(Collectors.toList())
                    .get(0);
            // 判断老人套餐中是否存在该菜品
            boolean exist = elderSetDishesList.contains(dishes);
            // 获取点该菜品的总份数
            Integer[] orderNum = {0};
            dishesNumMap.get(dishesId).forEach(orderDishesQuery -> orderNum[0] += orderDishesQuery.getOrderNum());
            // 计算菜品总额
            BigDecimal totalAmount = dishes.getPrice().multiply(BigDecimal.valueOf(orderNum[0]));
            // 计算实际总额
            BigDecimal reallyAmount = exist ?
                    totalAmount.subtract(dishes.getPrice()) :
                    totalAmount;
            // 计入支付总额
            payAmount = payAmount.add(reallyAmount);
            // 封装订单菜品实体
            OrderDishes orderDishes = new OrderDishes();
            orderDishes.setDishesName(dishes.getName());
            orderDishes.setDishesPrice(dishes.getPrice());
            orderDishes.setOrderNum(orderNum[0]);
            orderDishes.setSetFlag(exist ? YesNoEnum.YES.getCode() : YesNoEnum.NO.getCode());
            orderDishes.setTotalAmount(totalAmount);
            orderDishes.setReallyAmount(reallyAmount);
            orderDishesList.add(orderDishes);
        }
        // 初始化订单
        Order order = BeanUtil.toBean(query, Order.class);
        order.setPayAmount(payAmount);
        order.setOrderFlag(YesNoEnum.NO.getCode());
        // 新增订单
        orderMapper.insert(order);
        // 订单菜品设置订单编号
        orderDishesList.forEach(orderDishes -> orderDishes.setOrderId(order.getId()));
        // 批量插入订单菜品
        orderDishesFunc.saveBatch(orderDishesList);
        return Result.success();
    }

    @Override
    public Result getOrderById(Long orderId) {
        // 根据订单编号获取订单信息
        GetOrderByIdVo getOrderByIdVo = orderMapper.getOrderById(orderId);
        // 判断是否为空
        AssertUtil.notNull(getOrderByIdVo, ExceptionEnum.DATA_NOT_EXIST);
        // 根据订单编号获取订单菜品列表并转换实体
        List<GetOrderByIdVo.OrderDishesVo> orderDishesVoList = BeanUtil.copyToList(orderDishesFunc.listOrderDishesByOrderId(orderId), GetOrderByIdVo.OrderDishesVo.class);
        // 编序号
        orderDishesVoList = pageUtil.rank(orderDishesVoList);
        // 订单信息设置订单菜品
        getOrderByIdVo.setOrderDishesVoList(orderDishesVoList);
        return Result.success(getOrderByIdVo);
    }

    @Override
    @Transactional
    public Result sendOrder(SendOrderQuery query) {
        // 根据编号获取订单
        Order getOrderById = orderMapper.selectById(query.getId());
        // 判断订单是否已完成
        boolean checkOrderFlag = ObjUtil.isNotEmpty(getOrderById.getStaffId()) ||
                ObjUtil.isNotEmpty(getOrderById.getDeliverDishesDate()) ||
                Objects.equals(getOrderById.getOrderFlag(), YesNoEnum.YES.getCode());
        AssertUtil.notTrue(checkOrderFlag, ExceptionEnum.ORDER_SUCCESS);
        // 封装订单修改
        Order order = BeanUtil.toBean(query, Order.class);
        order.setOrderFlag(YesNoEnum.YES.getCode());
        // 修改订单
        orderMapper.updateById(order);
        // 老人扣费
        elderFunc.deductionFee(getOrderById.getElderId(), getOrderById.getPayAmount());
        // 新增消费记录
        consumeFunc.addConsume(getOrderById.getElderId(), ConsumeEnum.DISHES.getType(), getOrderById.getPayAmount(), getOrderById.getDineDate());
        return Result.success();
    }
}
