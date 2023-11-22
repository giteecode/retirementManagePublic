package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.dao.mapper.ContractMapper;
import com.ew.gerocomium.dao.po.Contract;
import com.ew.gerocomium.dao.query.OperateCheckContractQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 合同表公共方法
 */
@Component
public class ContractFunc {
    @Resource
    private ContractMapper contractMapper;

    /**
     * 根据老人编号获取老人合同
     *
     * @param elderId
     */
    public Contract getContractByElderId(Long elderId) {
        return contractMapper.selectOne(new LambdaQueryWrapper<Contract>()
                .eq(Contract::getElderId, elderId));
    }

    /**
     * 新增合同
     *
     * @param query
     * @param elderId
     */
    public void insertContract(OperateCheckContractQuery query, Long elderId) {
        // 删除合同
        contractMapper.delete(new LambdaQueryWrapper<Contract>()
                .eq(Contract::getElderId, elderId));
        // 初始化合同
        Contract contract = BeanUtil.toBean(query, Contract.class);
        contract.setId(null);
        contract.setElderId(elderId);
        // 新增
        contractMapper.insert(contract);
    }

    /**
     * 根据时间段获取合同列表
     *
     * @return
     */
    public List<Contract> listContractByTimePeriod(Date startTime, Date endTime) {
        return contractMapper.selectList(new LambdaQueryWrapper<Contract>()
                .ge(Contract::getSignDate, startTime)
                .le(Contract::getSignDate, endTime));
    }

    /**
     * 根据时间段获取合同列表
     *
     * @return
     */
    public List<Contract> listContractByTimePeriod(List<Contract> contractList, Date startTime, Date endTime) {
        return contractList.stream()
                .filter(contract -> startTime.before(contract.getSignDate()) && endTime.after(contract.getSignDate()))
                .collect(Collectors.toList());
    }

    /**
     * 根据时间段获取过期合同列表
     *
     * @return
     */
    public List<Contract> listExpireContractByTimePeriod(Date startTime, Date endTime) {
        return contractMapper.selectList(new LambdaQueryWrapper<Contract>()
                .ge(Contract::getEndDate, startTime)
                .le(Contract::getEndDate, endTime));
    }

    /**
     * 根据员工编号对合同进行分组
     *
     * @return
     */
    public Map<Long, List<Contract>> mapContractByStaffId(List<Contract> contractList) {
        return contractList.parallelStream().collect(Collectors.groupingBy(Contract::getStaffId));
    }
}
