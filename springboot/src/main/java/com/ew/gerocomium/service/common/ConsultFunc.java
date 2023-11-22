package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.dao.mapper.ConsultMapper;
import com.ew.gerocomium.dao.po.Consult;
import com.ew.gerocomium.dao.query.OperateConsultQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 咨询人表公共方法
 */
@Component
public class ConsultFunc {
    @Resource
    private ConsultMapper consultMapper;

    /**
     * 根据时间段获取咨询列表
     *
     * @return
     */
    public List<Consult> listConsultByTimePeriod(Date startTime, Date endTime) {
        return consultMapper.selectList(new LambdaQueryWrapper<Consult>()
                .and(consultLambdaQueryWrapper -> {
                    consultLambdaQueryWrapper.isNotNull(Consult::getId);
                    if (startTime != null) {
                        consultLambdaQueryWrapper.ge(Consult::getConsultDate, startTime);
                    }
                    if (endTime != null) {
                        consultLambdaQueryWrapper.le(Consult::getConsultDate, endTime);
                    }
                })
        );
    }

    /**
     * 根据时间段获取咨询列表
     *
     * @return
     */
    public List<Consult> listConsultByTimePeriod(List<Consult> consultList, Date startTime, Date endTime) {
        return consultList.stream()
                .filter(consult -> startTime.before(consult.getConsultDate()) && endTime.after(consult.getConsultDate()))
                .collect(Collectors.toList());
    }

    /**
     * 根据员工编号对咨询进行分组
     *
     * @return
     */
    public Map<Long, List<Consult>> mapConsultByStaffId(List<Consult> consultList) {
        return consultList.parallelStream().collect(Collectors.groupingBy(Consult::getStaffId));
    }

    /**
     * 根据来源渠道编号对咨询进行分组
     *
     * @return
     */
    public Map<Long, List<Consult>> mapConsultBySourceId(List<Consult> consultList) {
        return consultList.parallelStream().collect(Collectors.groupingBy(Consult::getSourceId));
    }

    /**
     * 初始化咨询人
     *
     * @param operateConsultQuery
     * @return
     */
    public Consult initConsult(OperateConsultQuery operateConsultQuery) {
        Consult consult = BeanUtil.toBean(operateConsultQuery, Consult.class);
        consult.setName(operateConsultQuery.getConsultName());
        consult.setPhone(operateConsultQuery.getConsultPhone());
        return consult;
    }
}
