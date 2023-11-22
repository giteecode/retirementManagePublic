package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.ElderLabelMapper;
import com.ew.gerocomium.dao.po.ElderLabel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 老人标签表公共方法
 */
@Component
public class ElderLabelFunc extends ServiceImpl<ElderLabelMapper, ElderLabel> {
    @Resource
    private ElderLabelMapper elderLabelMapper;

    /**
     * 根据标签编号和老人编号列表查询老人标签map
     *
     * @param elderIdList
     * @return
     */
    public Map<Long, List<ElderLabel>> mapElderLabel(List<Long> elderIdList) {
        LambdaQueryWrapper<ElderLabel> elderLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjUtil.isNotEmpty(elderIdList)) {
            elderIdList.forEach(elderId ->
                    elderLabelLambdaQueryWrapper.eq(ElderLabel::getElderId, elderId).or()
            );
        } else {
            elderLabelLambdaQueryWrapper.isNull(ElderLabel::getId);
        }
        List<ElderLabel> elderLabelList = elderLabelMapper.selectList(elderLabelLambdaQueryWrapper);
        return elderLabelList.parallelStream().collect(Collectors.groupingBy(ElderLabel::getElderId));
    }

    /**
     * 批量插入老人标签
     *
     * @param labelIdList
     * @param elderId
     */
    public void saveBatchElderLabel(List<Long> labelIdList, Long elderId) {
        // 删除原有老人标签
        elderLabelMapper.delete(new LambdaQueryWrapper<ElderLabel>()
                .eq(ElderLabel::getElderId, elderId));
        // 过滤重复值
        labelIdList = labelIdList.stream()
                .distinct()
                .collect(Collectors.toList());
        // 封装老人标签实体
        List<ElderLabel> elderLabelList = new ArrayList<>();
        labelIdList.forEach(labelId -> {
            ElderLabel elderLabel = new ElderLabel();
            elderLabel.setElderId(elderId);
            elderLabel.setLabelId(labelId);
            elderLabelList.add(elderLabel);
        });
        // 批量插入老人标签
        saveBatch(elderLabelList);
    }
}
