package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.EmergencyContactMapper;
import com.ew.gerocomium.dao.po.EmergencyContact;
import com.ew.gerocomium.dao.query.OperateCheckContractQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmergencyContactFunc extends ServiceImpl<EmergencyContactMapper, EmergencyContact> {
    @Resource
    private EmergencyContactMapper emergencyContactMapper;

    /**
     * 批量插入紧急联系人
     *
     * @param query
     * @param elderId
     * @param editFlag
     */
    public void saveBatchEmergencyContact(OperateCheckContractQuery query, Long elderId, Boolean editFlag) {
        // 若是修改
        if (editFlag) {
            emergencyContactMapper.delete(new LambdaQueryWrapper<EmergencyContact>()
                    .eq(EmergencyContact::getElderId, elderId));
        }
        // 初始化紧急联系人列表
        List<EmergencyContact> emergencyContactList = new ArrayList<>();
        // 封装紧急联系人列表
        query.getOperateEmergencyContactQueryList().forEach(operateEmergencyContactQuery -> {
            EmergencyContact emergencyContact = BeanUtil.toBean(operateEmergencyContactQuery, EmergencyContact.class);
            emergencyContact.setElderId(elderId);
            emergencyContactList.add(emergencyContact);
        });
        // 批量插入
        saveBatch(emergencyContactList);
    }

    /**
     * 根据老人编号获取紧急联系人
     *
     * @param elderId
     * @return
     */
    public List<OperateCheckContractQuery.OperateEmergencyContactQuery> listEmgencyContactByElderId(Long elderId) {
        // 根据老人编号获取紧急联系人
        List<EmergencyContact> emergencyContactList = emergencyContactMapper.selectList(new LambdaQueryWrapper<EmergencyContact>()
                .eq(EmergencyContact::getElderId, elderId));
        // 实体转换并返回结果
        return BeanUtil.copyToList(emergencyContactList, OperateCheckContractQuery.OperateEmergencyContactQuery.class);
    }
}
