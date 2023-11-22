package com.ew.gerocomium.service.impl;

import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.ConsumeMapper;
import com.ew.gerocomium.dao.query.PageConsumeByKeyQuery;
import com.ew.gerocomium.dao.vo.PageConsumeByKeyVo;
import com.ew.gerocomium.service.ConsumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {
    @Resource
    private ConsumeMapper consumeMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageConsumeByKey(PageConsumeByKeyQuery query) {
        // 获取开始/结束时间
        Date startTime = DateUtilWen.getDayStartTime(DateUtilWen.dateStrToDate(query.getStartTime()));
        Date endTime = DateUtilWen.getDayEndTime(DateUtilWen.dateStrToDate(query.getEndTime()));
        // 根据搜索关键字获取消费记录
        List<PageConsumeByKeyVo> pageConsumeByKeyVoList = consumeMapper.listConsumeByKey(query.getElderName(), startTime, endTime);
        // 封装返回数据
        PageResult<PageConsumeByKeyVo> pageResult = pageUtil.packPageResultData(pageConsumeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }
}
