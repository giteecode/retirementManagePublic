package com.ew.gerocomium.service.impl;

import com.ew.gerocomium.common.util.CommonUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.po.*;
import com.ew.gerocomium.dao.query.ClientSourceQuery;
import com.ew.gerocomium.dao.vo.AvailableBedVo;
import com.ew.gerocomium.dao.vo.MonthPerformanceRankVo;
import com.ew.gerocomium.dao.vo.TodayOverviewVo;
import com.ew.gerocomium.dao.vo.TodaySaleFollowVo;
import com.ew.gerocomium.service.HomeService;
import com.ew.gerocomium.service.common.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private CommonUtil commonUtil;
    @Resource
    private ConsultFunc consultFunc;
    @Resource
    private ReserveFunc reserveFunc;
    @Resource
    private ContractFunc contractFunc;
    @Resource
    private RoomFunc roomFunc;
    @Resource
    private BedFunc bedFunc;
    @Resource
    private VisitPlanFunc visitPlanFunc;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private SourceFunc sourceFunc;

    @Override
    public Result todayOverview() {
        // 获取今日开始/结束时间
        Date date = new Date();
        Date startTime = DateUtilWen.getDayStartTime(date);
        Date endTime = DateUtilWen.getDayEndTime(date);
        // 获取今日新增咨询
        List<Consult> todayConsultList = consultFunc.listConsultByTimePeriod(startTime, endTime);
        // 获取今日新增预定
        List<Reserve> todayReserveList = reserveFunc.listReserveByTimePeriod(startTime, endTime);
        // 获取今日新增合同
        List<Contract> todayContractList = contractFunc.listContractByTimePeriod(startTime, endTime);
        // 获取今日到期合同
        List<Contract> todayContractExpireList = contractFunc.listExpireContractByTimePeriod(startTime, endTime);
        // 封装返回数据
        TodayOverviewVo todayOverviewVo = new TodayOverviewVo();
        todayOverviewVo.setTodayAddConsultNum((long) todayConsultList.size());
        todayOverviewVo.setTodayAddReserveNum((long) todayReserveList.size());
        todayOverviewVo.setTodayAddContractNum((long) todayContractList.size());
        todayOverviewVo.setTodayContractExpireNum((long) todayContractExpireList.size());
        return Result.success(todayOverviewVo);
    }

    @Override
    public Result availableBed() {
        // 获取所有未被删除房间
        List<Room> roomList = roomFunc.listNotDelRoom();
        // 获取所有未被删除床位
        List<Bed> bedList = bedFunc.listNotDelBed();
        // 获取空闲房间（房间内有床位且都是空闲状态）
        List<Room> idleRoomList = roomFunc.listIdleRoom(roomList, bedList);
        // 获取空闲床位
        List<Bed> idleBedList = bedFunc.listIdleBed(bedList);
        // 获取已登记退床
        List<Bed> exitAuditList = bedFunc.listExitAuditBed(bedList);
        // 封装返回数据
        AvailableBedVo availableBedVo = new AvailableBedVo();
        availableBedVo.setIdleRoomNum((long) idleRoomList.size());
        availableBedVo.setIdleBedNum((long) idleBedList.size());
        availableBedVo.setExitAuditNum((long) exitAuditList.size());
        return Result.success(availableBedVo);
    }

    @Override
    public Result todaySaleFollow() {
        // 获取今日开始/结束时间
        Date date = new Date();
        Date startTime = DateUtilWen.getDayStartTime(date);
        Date endTime = DateUtilWen.getDayEndTime(date);
        // 获取所有未被删除回访计划
        List<VisitPlan> visitPlanList = visitPlanFunc.listNotDelVisitPlan();
        // 获取今日应回访
        List<VisitPlan> todayReturnVisitList = visitPlanFunc.listReturnVisitPlanByTimePeriod(visitPlanList, startTime, endTime);
        // 获取今日已回访
        List<VisitPlan> todayReturnedVisitList = visitPlanFunc.listReturnedVisitPlanByTimePeriod(visitPlanList, startTime, endTime);
        // 获取待回访
        List<VisitPlan> stayReturnedVisitList = visitPlanFunc.listStayReturnVisitPlanByTimePeriod(visitPlanList, startTime, endTime);
        // 封装返回数据
        TodaySaleFollowVo todaySaleFollowVo = new TodaySaleFollowVo();
        todaySaleFollowVo.setTodayReturnVisitNum((long) todayReturnVisitList.size());
        todaySaleFollowVo.setTodayReturnedVisitNum((long) todayReturnedVisitList.size());
        todaySaleFollowVo.setStayReturnedVisitNum((long) stayReturnedVisitList.size());
        return Result.success(todaySaleFollowVo);
    }

    @Override
    public Result monthPerformanceRank() {
        // 当前时间
        Date date = new Date();
        // 上月当前时间
        Date nowAroundTime = DateUtilWen.getNowMonthAroundTime(date, -1);
        // 上月开始/结束时间
        Date lastMonthStartTime = DateUtilWen.getMonthFirstDay(nowAroundTime);
        Date lastMonthEndTime = DateUtilWen.getMonthLastDay(nowAroundTime);
        // 本月开始/结束时间
        Date thisMonthStartTime = DateUtilWen.getMonthFirstDay(date);
        Date thisMonthEndTime = DateUtilWen.getMonthLastDay(date);
        // 获取上月和本月所有咨询客户记录
        List<Consult> consultList = consultFunc.listConsultByTimePeriod(lastMonthStartTime, thisMonthEndTime);
        // 分别获取上月和本月咨询客户记录
        List<Consult> lastMonthConsultList = consultFunc.listConsultByTimePeriod(consultList, lastMonthStartTime, lastMonthEndTime);
        List<Consult> thisMonthConsultList = consultFunc.listConsultByTimePeriod(consultList, thisMonthStartTime, thisMonthEndTime);
        // 获取上月和本月所有签订合同记录
        List<Contract> contractList = contractFunc.listContractByTimePeriod(lastMonthStartTime, thisMonthEndTime);
        // 分别获取上月和本月签订合同记录
        List<Contract> lastMonthContractList = contractFunc.listContractByTimePeriod(contractList, lastMonthStartTime, lastMonthEndTime);
        List<Contract> thisMonthContractList = contractFunc.listContractByTimePeriod(contractList, thisMonthStartTime, thisMonthEndTime);
        // 获取列表size
        long lastMonthConsultSize = lastMonthConsultList.size();
        long thisMonthConsultSize = thisMonthConsultList.size();
        long lastMonthContractSize = lastMonthContractList.size();
        long thisMonthContractSize = thisMonthContractList.size();
        // 获取上月和本月合同和咨询比值（转换率）
        Double lastRatio = commonUtil.getRatio((double) lastMonthContractSize, (double) lastMonthConsultSize);
        Double thisRatio = commonUtil.getRatio((double) thisMonthContractSize, (double) thisMonthConsultSize);
        // 封装返回数据
        MonthPerformanceRankVo monthPerformanceRankVo = new MonthPerformanceRankVo();
        monthPerformanceRankVo.setConsultClientNum(thisMonthConsultSize);
        monthPerformanceRankVo.setConsultClientFloatRate(commonUtil.getGrowthRate((double) lastMonthConsultSize, (double) thisMonthConsultSize));
        monthPerformanceRankVo.setSignContractNum(thisMonthContractSize);
        monthPerformanceRankVo.setSignContractFloatRate(commonUtil.getGrowthRate((double) lastMonthContractSize, (double) thisMonthContractSize));
        monthPerformanceRankVo.setConsultConversionRate(thisRatio);
        monthPerformanceRankVo.setConsultConversionFloatRate(commonUtil.getGrowthRate(lastRatio, thisRatio));
        monthPerformanceRankVo.setSaleRankList(staffFunc.listSaleRank(thisMonthConsultList, thisMonthContractList));
        return Result.success(monthPerformanceRankVo);
    }

    @Override
    public Result clientSource(ClientSourceQuery query) {
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 获取来源渠道列表
        List<Source> sourceList = sourceFunc.listNotDelSource(null);
        // 获取咨询人列表
        List<Consult> consultList = consultFunc.listConsultByTimePeriod(startTime, endTime);
        return Result.success(sourceFunc.statClientSource(sourceList, consultList));
    }

    @Override
    public Result businessTrend() {
        // 当前时间
        Date date = new Date();
        // 获取今年开始/结束时间
        Date yearStartTime = DateUtilWen.getYearFirstDay(date);
        Date yearEndTime = DateUtilWen.getYearLastDay(date);
        // 获取今年所有咨询客户记录
        List<Consult> consultList = consultFunc.listConsultByTimePeriod(yearStartTime, yearEndTime);
        // 获取今年所有签订合同记录
        List<Contract> contractList = contractFunc.listContractByTimePeriod(yearStartTime, yearEndTime);
        return Result.success(staffFunc.listBusinessTrend(consultList, contractList, yearStartTime));
    }
}
