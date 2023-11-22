package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.mapper.VisitPlanMapper;
import com.ew.gerocomium.dao.po.VisitPlan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 回访计划公共方法
 */
@Component
public class VisitPlanFunc {
    @Resource
    private VisitPlanMapper visitPlanMapper;

    /**
     * 获取所有未被删除的回访计划
     *
     * @return
     */
    public List<VisitPlan> listNotDelVisitPlan() {
        return visitPlanMapper.selectList(new LambdaQueryWrapper<VisitPlan>()
                .eq(VisitPlan::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 根据条件获取所有未被删除的回访计划
     *
     * @param elderId
     * @param completeFlag
     * @return
     */
    public List<VisitPlan> listNotDelVisitPlan(Long elderId, Boolean completeFlag) {
        return visitPlanMapper.selectList(new LambdaQueryWrapper<VisitPlan>()
                .and(visitPlanLambdaQueryWrapper ->
                        visitPlanLambdaQueryWrapper
                                .eq(VisitPlan::getElderId, elderId)
                                .eq(VisitPlan::getDelFlag, YesNoEnum.NO.getCode()))
                .and(visitPlanLambdaQueryWrapper -> {
                    if (completeFlag) {
                        visitPlanLambdaQueryWrapper.isNotNull(VisitPlan::getContent).or()
                                .isNotNull(VisitPlan::getCompleteDate);
                    } else {
                        visitPlanLambdaQueryWrapper.isNull(VisitPlan::getContent).or()
                                .isNull(VisitPlan::getCompleteDate);
                    }
                })
        );
    }

    /**
     * 根据标题获取回访计划
     *
     * @param elderId
     * @param title
     * @return
     */
    public VisitPlan getVisitPlanByTitle(Long elderId, String title) {
        return visitPlanMapper.selectOne(new LambdaQueryWrapper<VisitPlan>()
                .eq(VisitPlan::getElderId, elderId)
                .eq(VisitPlan::getTitle, title)
                .eq(VisitPlan::getDelFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 根据时间段获取应回访列表
     *
     * @param notDelVisitPlanList
     * @param startTime
     * @param endTime
     * @return
     */
    public List<VisitPlan> listReturnVisitPlanByTimePeriod(List<VisitPlan> notDelVisitPlanList, Date startTime, Date endTime) {
        return notDelVisitPlanList.stream()
                .filter(visitPlan -> startTime.before(visitPlan.getPlanDate()) && endTime.after(visitPlan.getPlanDate()))
                .collect(Collectors.toList());
    }

    /**
     * 根据时间段获取已回访列表
     *
     * @param notDelVisitPlanList
     * @param startTime
     * @param endTime
     * @return
     */
    public List<VisitPlan> listReturnedVisitPlanByTimePeriod(List<VisitPlan> notDelVisitPlanList, Date startTime, Date endTime) {
        return notDelVisitPlanList.stream()
                .filter(visitPlan -> visitPlan.getCompleteDate() != null &&
                        startTime.before(visitPlan.getCompleteDate()) &&
                        endTime.after(visitPlan.getCompleteDate())
                )
                .collect(Collectors.toList());
    }

    /**
     * 根据时间段获取待回访列表
     *
     * @param notDelVisitPlanList
     * @param startTime
     * @param endTime
     * @return
     */
    public List<VisitPlan> listStayReturnVisitPlanByTimePeriod(List<VisitPlan> notDelVisitPlanList, Date startTime, Date endTime) {
        return notDelVisitPlanList.stream()
                .filter(visitPlan -> visitPlan.getContent() == null || visitPlan.getCompleteDate() == null)
                .collect(Collectors.toList());
    }
}
