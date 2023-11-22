package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;

public interface IntentionService {
    /**
     * 客户标签
     *
     * @return
     */
    Result listLabel();

    /**
     * 分页查询意向客户
     *
     * @param pageIntentionByKeyQuery
     * @return
     */
    Result pageIntentionByKey(PageIntentionByKeyQuery pageIntentionByKeyQuery);

    /**
     * 分页查询咨询老人请求实体
     *
     * @param pageSearchElderByKeyQuery
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery pageSearchElderByKeyQuery);

    /**
     * 新增意向客户
     *
     * @param operateIntentionQuery
     * @return
     */
    Result addIntention(OperateIntentionQuery operateIntentionQuery);

    /**
     * 根据编号获取意向客户
     *
     * @param elderId
     * @return
     */
    Result getIntentById(Long elderId);

    /**
     * 根据编号获取意向客户标签
     *
     * @param elderId
     * @return
     */
    Result getElderLabelById(Long elderId);

    /**
     * 编辑意向客户
     *
     * @param operateIntentionQuery
     * @return
     */
    Result editIntention(OperateIntentionQuery operateIntentionQuery);

    /**
     * 根据编号获取编辑意向客户标签
     *
     * @param elderId
     * @return
     */
    Result getEditElderLabelById(Long elderId);

    /**
     * 编辑老人标签
     *
     * @param editElderLabelQuery
     * @return
     */
    Result editElderLabel(EditElderLabelQuery editElderLabelQuery);

    /**
     * 分页查询回访计划
     *
     * @param pageVisitPlanQuery
     * @return
     */
    Result pageVisitPlan(PageVisitPlanQuery pageVisitPlanQuery);

    /**
     * 新增回访计划
     *
     * @param addVisitPlanQuery
     * @return
     */
    Result addVisitPlan(AddVisitPlanQuery addVisitPlanQuery);

    /**
     * 执行回访计划
     *
     * @param completeVisitPlanQuery
     * @return
     */
    Result executeVisitPlan(CompleteVisitPlanQuery completeVisitPlanQuery);

    /**
     * 删除回访计划
     *
     * @param visitPlanId
     * @return
     */
    Result deleteVisitPlan(Long visitPlanId);

    /**
     * 分页查询沟通记录
     *
     * @param pageCommunicationRecordQuery
     * @return
     */
    Result pageCommunicationRecord(PageCommunicationRecordQuery pageCommunicationRecordQuery);

    /**
     * 新增沟通记录
     *
     * @param addCommunicationRecordQuery
     * @return
     */
    Result addCommunicationRecord(AddCommunicationRecordQuery addCommunicationRecordQuery);

    /**
     * 编辑沟通记录
     *
     * @param editCommunicationRecordQuery
     * @return
     */
    Result editCommunicationRecord(EditCommunicationRecordQuery editCommunicationRecordQuery);

    /**
     * 删除沟通记录
     *
     * @param communicationRecordId
     * @return
     */
    Result deleteCommunicationRecord(Long communicationRecordId);
}
