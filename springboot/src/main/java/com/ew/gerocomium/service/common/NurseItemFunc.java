package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.NurseItemMapper;
import com.ew.gerocomium.dao.po.NurseItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 护理项目表公共方法
 */
@Component
public class NurseItemFunc extends ServiceImpl<NurseItemMapper, NurseItem> {
    @Resource
    private NurseItemMapper nurseItemMapper;

    /**
     * 批量插入护理项目
     *
     * @param serviceIdList
     * @param gradeId
     * @param editFlag
     */
    public void saveBatchActiveParticipant(List<Long> serviceIdList, Long gradeId, Boolean editFlag) {
        // 删除原有护理项目
        if (editFlag) {
            nurseItemMapper.delete(new LambdaQueryWrapper<NurseItem>()
                    .eq(NurseItem::getGradeId, gradeId));
        }
        // 过滤重复值
        serviceIdList = serviceIdList.stream()
                .distinct()
                .collect(Collectors.toList());
        // 封装护理项目实体
        List<NurseItem> nurseItemList = new ArrayList<>();
        serviceIdList.forEach(serviceId -> {
            NurseItem nurseItem = new NurseItem();
            nurseItem.setGradeId(gradeId);
            nurseItem.setServiceId(serviceId);
            nurseItemList.add(nurseItem);
        });
        // 批量插入护理项目
        saveBatch(nurseItemList);
    }
}
