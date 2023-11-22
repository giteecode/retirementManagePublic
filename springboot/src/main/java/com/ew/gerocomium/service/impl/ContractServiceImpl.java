package com.ew.gerocomium.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.util.CommonUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.SendEmailUtil;
import com.ew.gerocomium.dao.mapper.ContractMapper;
import com.ew.gerocomium.dao.mapper.EmergencyContactMapper;
import com.ew.gerocomium.dao.vo.ExpireContractEmergencyContactVo;
import com.ew.gerocomium.dao.vo.ExpireContractVo;
import com.ew.gerocomium.service.ContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private EmergencyContactMapper emergencyContactMapper;
    @Resource
    private CommonUtil commonUtil;

    @Override
    public void contractExpireJob() {
        // 获取后7天当前时间
        Date nowDayAroundTime = DateUtilWen.getNowDayAroundTime(new Date(), 7);
        // 获取后7天结束时间
        Date endTime = DateUtilWen.getDayEndTime(nowDayAroundTime);
        // 获取已过期和即将到期的合同
        List<ExpireContractVo> expireContractVoList = contractMapper.listExpireContract(endTime);
        // 验证合同列表是否为空
        if (ObjUtil.isEmpty(expireContractVoList)) {
            return;
        }
        // 获取老人编号
        List<Long> elderIdList = new ArrayList<>();
        expireContractVoList.forEach(expireContractVo -> elderIdList.add(expireContractVo.getElderId()));
        // 根据老人编号列表获取紧急联系人列表
        List<ExpireContractEmergencyContactVo> expireContractEmergencyContactVoList =
                emergencyContactMapper.listExpireContractEmergencyContactVoByElderIdList(elderIdList);
        // 验证紧急联系人列表是否为空
        if (ObjUtil.isEmpty(expireContractEmergencyContactVoList)) {
            return;
        }
        // 将紧急联系人根据老人编号分组
        Map<Long, List<ExpireContractEmergencyContactVo>> emergencyContactVoByElderMap =
                expireContractEmergencyContactVoList
                        .parallelStream()
                        .collect(Collectors.groupingBy(ExpireContractEmergencyContactVo::getElderId));
        // 循环发送验证码
        expireContractVoList.forEach(expireContractVo -> {
            // 获取老人紧急联系人列表
            List<ExpireContractEmergencyContactVo> emergencyContactVoList = emergencyContactVoByElderMap.get(expireContractVo.getElderId());
            // 若老人有紧急联系人
            if (ObjUtil.isNotEmpty(emergencyContactVoList)) {
                // 实例化邮箱列表
                List<String> emailList = new ArrayList<>();
                // 获取紧急联系人列表迭代器
                Iterator<ExpireContractEmergencyContactVo> iterator = emergencyContactVoList.stream().iterator();
                // 循环添加紧急联系人邮箱
                while (iterator.hasNext()) {
                    emailList.add(iterator.next().getEmail());
                }
                // 邮箱去重
                emailList = emailList.stream().distinct().collect(Collectors.toList());
                // 获取发送邮箱内容
                String emailContent = commonUtil.joinEmailContent(expireContractVo);
                // 发送邮件
                SendEmailUtil.sendEmail(emailList, emailContent);
            }
        });
    }
}
