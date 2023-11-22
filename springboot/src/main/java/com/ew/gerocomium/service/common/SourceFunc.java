package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.SourceMapper;
import com.ew.gerocomium.dao.po.Consult;
import com.ew.gerocomium.dao.po.Source;
import com.ew.gerocomium.dao.vo.ClientSourceVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 来源渠道表公共方法
 */
@Component
public class SourceFunc {
    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private ConsultFunc consultFunc;

    /**
     * 获取所有未被删除的来源渠道
     *
     * @return
     */
    public List<Source> listNotDelSource(String searchKey) {
        return sourceMapper.selectList(new LambdaQueryWrapper<Source>()
                .and(sourceLambdaQueryWrapper -> {
                    sourceLambdaQueryWrapper.eq(Source::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(searchKey)) {
                        sourceLambdaQueryWrapper.like(Source::getName, searchKey);
                    }
                })
                .orderByDesc(Source::getCreateTime)
        );
    }

    /**
     * 根据名称获取来源渠道
     *
     * @return
     */
    public Source getSourceByName(String sourceName) {
        return sourceMapper.selectOne(new LambdaQueryWrapper<Source>()
                .eq(Source::getName, sourceName)
                .eq(Source::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 统计客户来源渠道
     *
     * @return
     */
    public List<ClientSourceVo> statClientSource(List<Source> sourceList, List<Consult> consultList) {
        // 根据来源渠道编号对咨询进行分组
        Map<Long, List<Consult>> mapConsultBySourceId = consultFunc.mapConsultBySourceId(consultList);
        // 封装返回数据
        List<ClientSourceVo> clientSourceVoList = new ArrayList<>();
        for (Source source : sourceList) {
            ClientSourceVo clientSourceVo = new ClientSourceVo();
            long consultNum = 0;
            // 获取该来源渠道咨询人数
            for (Long sourceId : mapConsultBySourceId.keySet()) {
                if (Objects.equals(source.getId(), sourceId)) {
                    consultNum = mapConsultBySourceId.get(sourceId).size();
                }
            }
            clientSourceVo.setSourceName(source.getName());
            clientSourceVo.setConsultNum(consultNum);
            clientSourceVoList.add(clientSourceVo);
        }
        return clientSourceVoList;
    }
}
