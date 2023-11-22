package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.NurseGradeMapper;
import com.ew.gerocomium.dao.mapper.NurseItemMapper;
import com.ew.gerocomium.dao.po.NurseGrade;
import com.ew.gerocomium.dao.vo.GetNurseGradeByIdVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 护理等级表公共方法
 */
@Component
public class NurseGradeFunc {
    @Resource
    private NurseGradeMapper nurseGradeMapper;
    @Resource
    private NurseItemMapper nurseItemMapper;

    /**
     * 获取未被删除的护理等级
     *
     * @return
     */
    public List<NurseGrade> listNotDelNurseGrade() {
        return nurseGradeMapper.selectList(
                new LambdaQueryWrapper<NurseGrade>()
                        .eq(NurseGrade::getDelFlag, YesNoEnum.NO.getCode())
        );
    }

    /**
     * 获取未被删除的护理等级
     *
     * @param gradeName
     * @param nurseType
     * @return
     */
    public List<NurseGrade> listNotDelNurseGrade(String gradeName, String nurseType) {
        return nurseGradeMapper.selectList(new LambdaQueryWrapper<NurseGrade>()
                .and(nurseGradeLambdaQueryWrapper -> {
                    nurseGradeLambdaQueryWrapper
                            .eq(NurseGrade::getDelFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(gradeName)) {
                        nurseGradeLambdaQueryWrapper
                                .eq(NurseGrade::getName, gradeName);
                    }
                    if (ObjUtil.isNotEmpty(nurseType)) {
                        nurseGradeLambdaQueryWrapper
                                .eq(NurseGrade::getType, nurseType);
                    }
                }));
    }

    /**
     * 根据编号获取护理等级
     *
     * @param nurseGradeId
     * @return
     */
    public GetNurseGradeByIdVo getNurseGradeById(Long nurseGradeId) {
        // 根据编号获取护理等级
        NurseGrade nurseGrade = nurseGradeMapper.selectById(nurseGradeId);
        // 判断是否为空
        AssertUtil.notNull(nurseGrade, ExceptionEnum.DATA_NOT_EXIST);
        // 转换实体
        GetNurseGradeByIdVo getNurseGradeByIdVo = BeanUtil.toBean(nurseGrade, GetNurseGradeByIdVo.class);
        // 根据护理等级编号获取服务列表并设值
        List<GetNurseGradeByIdVo.NurseGradeServiceVo> nurseGradeServiceVoList = nurseItemMapper.listGradeService(nurseGradeId);
        getNurseGradeByIdVo.setNurseGradeServiceVoList(nurseGradeServiceVoList);
        // 获取服务编号列表并设值
        List<Long> serviceIdList = new ArrayList<>();
        nurseGradeServiceVoList.forEach(serviceVo -> serviceIdList.add(serviceVo.getId()));
        getNurseGradeByIdVo.setServiceIdList(serviceIdList);
        return getNurseGradeByIdVo;
    }

    /**
     * 根据护理等级名称获取信息
     *
     * @param gradeName
     * @return
     */
    public NurseGrade getNurseGradeByName(String gradeName) {
        return nurseGradeMapper.selectOne(new LambdaQueryWrapper<NurseGrade>()
                .eq(NurseGrade::getName, gradeName)
                .eq(NurseGrade::getDelFlag, YesNoEnum.NO.getCode()));
    }
}
