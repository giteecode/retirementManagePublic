package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.ActiveParticipantMapper;
import com.ew.gerocomium.dao.po.ActiveParticipant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 活动参与表公共方法
 */
@Component
public class ActiveParticipantFunc extends ServiceImpl<ActiveParticipantMapper, ActiveParticipant> {
    @Resource
    private ActiveParticipantMapper activeParticipantMapper;

    /**
     * 批量插入活动参与者
     *
     * @param elderIdList
     * @param activeId
     * @param editFlag
     */
    public void saveBatchActiveParticipant(List<Long> elderIdList, Long activeId, Boolean editFlag) {
        // 删除原有活动参与者
        if (editFlag) {
            activeParticipantMapper.delete(new LambdaQueryWrapper<ActiveParticipant>()
                    .eq(ActiveParticipant::getActiveId, activeId));
        }
        // 过滤重复值
        elderIdList = elderIdList.stream()
                .distinct()
                .collect(Collectors.toList());
        // 封装活动参与实体
        List<ActiveParticipant> activeParticipantList = new ArrayList<>();
        elderIdList.forEach(elderId -> {
            ActiveParticipant activeParticipant = new ActiveParticipant();
            activeParticipant.setActiveId(activeId);
            activeParticipant.setElderId(elderId);
            activeParticipantList.add(activeParticipant);
        });
        // 批量插入活动参与者
        saveBatch(activeParticipantList);
    }
}
