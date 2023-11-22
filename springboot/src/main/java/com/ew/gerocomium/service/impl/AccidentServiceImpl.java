package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.AccidentMapper;
import com.ew.gerocomium.dao.po.Accident;
import com.ew.gerocomium.dao.query.AddAccidentQuery;
import com.ew.gerocomium.dao.query.EditAccidentQuery;
import com.ew.gerocomium.dao.query.PageAccidentByKeyQuery;
import com.ew.gerocomium.dao.vo.GetAccidentByIdVo;
import com.ew.gerocomium.dao.vo.PageAccidentByKeyVo;
import com.ew.gerocomium.service.AccidentService;
import com.ew.gerocomium.service.common.AccidentFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccidentServiceImpl implements AccidentService {
    @Resource
    private AccidentMapper accidentMapper;
    @Resource
    private AccidentFunc accidentFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageAccidentByKey(PageAccidentByKeyQuery query) {
        // 根据关键字获取事故登记列表
        List<PageAccidentByKeyVo> pageAccidentByKeyVoList = accidentMapper.listAccidentByKeyVo(query);
        // 封装返回数据
        PageResult<PageAccidentByKeyVo> pageResult = pageUtil.packPageResultData(pageAccidentByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addAccident(AddAccidentQuery query) {
        // 初始化事故登记
        query.setId(null);
        Accident accident = BeanUtil.toBean(query, Accident.class);
        accident.setOccurDate(DateUtilWen.dateStrToDate(query.getOccurDate()));
        accident.setDelFlag(YesNoEnum.NO.getCode());
        // 新增
        accidentMapper.insert(accident);
        return Result.success();
    }

    @Override
    public Result getAccidentById(Long accidentId) {
        // 根据编号获取事故登记
        GetAccidentByIdVo accidentById = accidentMapper.getAccidentById(accidentId);
        // 验证事故登记是否存在
        AssertUtil.notNull(accidentById, ExceptionEnum.DATA_NOT_EXIST);
        return Result.success(accidentById);
    }

    @Override
    public Result editAccident(EditAccidentQuery query) {
        // 验证事故登记
        Accident accident = accidentFunc.checkAccident(query.getId(), false);
        // 封装修改
        BeanUtil.copyProperties(query, accident);
        accident.setOccurDate(DateUtilWen.dateStrToDate(query.getOccurDate()));
        // 修改
        accidentMapper.updateById(accident);
        return Result.success();
    }

    @Override
    public Result deleteAccident(Long accidentId) {
        // 验证事故登记
        Accident accident = accidentFunc.checkAccident(accidentId, true);
        // 封装修改
        accident.setDelFlag(YesNoEnum.YES.getCode());
        // 修改
        accidentMapper.updateById(accident);
        return Result.success();
    }
}
