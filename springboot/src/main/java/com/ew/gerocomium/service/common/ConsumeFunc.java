package com.ew.gerocomium.service.common;

import com.ew.gerocomium.dao.mapper.ConsumeMapper;
import com.ew.gerocomium.dao.po.Consume;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费记录表公共方法
 */
@Component
public class ConsumeFunc {
    @Resource
    private ConsumeMapper consumeMapper;

    /**
     * 添加消费记录
     *
     * @param elderId
     * @param consumeType
     * @param payAmount
     * @param consumeDate
     */
    public void addConsume(Long elderId, String consumeType, BigDecimal payAmount, Date consumeDate) {
        // 初始化消费记录
        Consume consume = new Consume(elderId, consumeType, payAmount, consumeDate);
        // 新增消费记录
        consumeMapper.insert(consume);
    }
}
