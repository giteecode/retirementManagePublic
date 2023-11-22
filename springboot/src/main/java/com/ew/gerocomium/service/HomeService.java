package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.ClientSourceQuery;

public interface HomeService {
    /**
     * 今日概览
     *
     * @return
     */
    Result todayOverview();

    /**
     * 可售床位
     *
     * @return
     */
    Result availableBed();

    /**
     * 今日销售跟进
     *
     * @return
     */
    Result todaySaleFollow();

    /**
     * 本月业绩排行
     *
     * @return
     */
    Result monthPerformanceRank();

    /**
     * 客户来源渠道
     *
     * @param clientSourceQuery
     * @return
     */
    Result clientSource(ClientSourceQuery clientSourceQuery);

    /**
     * 业务趋势
     *
     * @return
     */
    Result businessTrend();
}
