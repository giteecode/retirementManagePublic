package com.ew.gerocomium.service.common;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.ReserveMapper;
import com.ew.gerocomium.dao.po.Reserve;
import com.ew.gerocomium.dao.query.AddReserveQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 预定表公共方法
 */
@Component
public class ReserveFunc extends ServiceImpl<ReserveMapper, Reserve> {
    @Resource
    private ReserveMapper reserveMapper;

    /**
     * 根据时间段获取预定列表
     *
     * @return
     */
    public List<Reserve> listReserveByTimePeriod(Date startTime, Date endTime) {
        return reserveMapper.selectList(new LambdaQueryWrapper<Reserve>()
                .ge(Reserve::getCreateTime, startTime)
                .le(Reserve::getCreateTime, endTime));
    }

    /**
     * 初始化预定
     *
     * @param addReserveQuery
     * @param elderId
     * @return
     */
    public Reserve operateReserveInitElder(AddReserveQuery addReserveQuery, Long elderId) {
        Reserve reserve = new Reserve();
        reserve.setElderId(elderId);
        reserve.setStaffId(addReserveQuery.getStaffId());
        reserve.setName(addReserveQuery.getPayerName());
        reserve.setPhone(addReserveQuery.getPayerPhone());
        reserve.setDueDate(DateUtil.parseDate(addReserveQuery.getDueDate()));
        reserve.setDeposit(addReserveQuery.getDeposit());
        reserve.setReserveFlag(YesNoEnum.NO.getCode());
        return reserve;
    }
}
