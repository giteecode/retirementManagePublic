package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.OperateLabelQuery;
import com.ew.gerocomium.dao.query.OperateLabelTypeQuery;

public interface LabelService {
    /**
     * 客户标签列表
     *
     * @return
     */
    Result listLabel();

    /**
     * 新增标签分类
     *
     * @param operateLabelTypeQuery
     * @return
     */
    Result addLabelType(OperateLabelTypeQuery operateLabelTypeQuery);

    /**
     * 根据编号获取标签分类信息
     *
     * @param typeId
     * @return
     */
    Result getLabelTypeById(Long typeId);

    /**
     * 编辑标签分类
     *
     * @param operateLabelTypeQuery
     * @return
     */
    Result editLabelType(OperateLabelTypeQuery operateLabelTypeQuery);

    /**
     * 删除标签分类
     *
     * @param typeId
     * @return
     */
    Result deleteLabelType(Long typeId);

    /**
     * 新增标签
     *
     * @param operateLabelQuery
     * @return
     */
    Result addLabel(OperateLabelQuery operateLabelQuery);

    /**
     * 根据编号获取标签信息
     *
     * @param labelId
     * @return
     */
    Result getLabelById(Long labelId);

    /**
     * 编辑标签
     *
     * @param operateLabelQuery
     * @return
     */
    Result editLabel(OperateLabelQuery operateLabelQuery);

    /**
     * 删除标签
     *
     * @param labelId
     * @return
     */
    Result deleteLabel(Long labelId);
}
