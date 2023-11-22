package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.VisitEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.VisitMapper;
import com.ew.gerocomium.dao.po.Visit;
import com.ew.gerocomium.dao.query.AddVisitQuery;
import com.ew.gerocomium.dao.query.EditVisitQuery;
import com.ew.gerocomium.dao.query.PageVisitByKeyQuery;
import com.ew.gerocomium.dao.query.RecordLeaveQuery;
import com.ew.gerocomium.dao.vo.GetVisitByIdVo;
import com.ew.gerocomium.dao.vo.PageVisitByKeyVo;
import com.ew.gerocomium.service.VisitService;
import com.ew.gerocomium.service.common.VisitFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Resource
    private VisitMapper visitMapper;
    @Resource
    private VisitFunc visitFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageVisitByKey(PageVisitByKeyQuery query) {
        // 根据关键字获取来访登记
        List<PageVisitByKeyVo> pageVisitByKeyVoList = visitMapper.listVisitByKey(query);
        // 封装返回数据
        PageResult<PageVisitByKeyVo> pageResult = pageUtil.packPageResultData(pageVisitByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addVisit(AddVisitQuery query) {
        // 初始化来访登记
        query.setId(null);
        Visit visit = BeanUtil.toBean(query, Visit.class);
        visit.setVisitDate(DateUtilWen.dateStrToDate(query.getVisitDate()));
        visit.setVisitFlag(VisitEnum.STAY_LEAVE.getStatus());
        visit.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        visitMapper.insert(visit);
        return Result.success();
    }

    @Override
    public Result getVisitById(Long visitId) {
        // 根据编号获取来访登记
        GetVisitByIdVo getVisitByIdVo = visitMapper.getVisitById(visitId);
        // 验证来访登记是否存在
        AssertUtil.notNull(getVisitByIdVo, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(getVisitByIdVo);
    }

    @Override
    public Result editVisit(EditVisitQuery query) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(query.getId(), false);
        // 封装修改
        BeanUtil.copyProperties(query, visit);
        visit.setVisitDate(DateUtilWen.dateStrToDate(query.getVisitDate()));
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }

    @Override
    public Result recordLeave(RecordLeaveQuery query) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(query.getId(), false);
        // 封装修改
        visit.setLeaveDate(DateUtilWen.dateStrToDate(query.getLeaveDate()));
        visit.setVisitFlag(VisitEnum.ALREADY_LEAVE.getStatus());
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }

    @Override
    public Result deleteVisit(Long visitId) {
        // 验证来访登记
        Visit visit = visitFunc.checkVisit(visitId, true);
        // 封装修改
        visit.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        visitMapper.updateById(visit);
        return Result.success();
    }
}
