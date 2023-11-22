package com.ew.gerocomium.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.AuditEnum;
import com.ew.gerocomium.common.constant.BedEnum;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.mapper.RetreatApplyMapper;
import com.ew.gerocomium.dao.po.Bed;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.po.RetreatApply;
import com.ew.gerocomium.dao.query.PageRetreatApplyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;
import com.ew.gerocomium.dao.vo.PageRetreatByKeyVo;
import com.ew.gerocomium.dao.vo.PageSearchElderByKeyVo;
import com.ew.gerocomium.service.RetreatApplyService;
import com.ew.gerocomium.service.common.CommonFunc;
import com.ew.gerocomium.service.common.RetreatApplyFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class RetreatApplyServiceImpl implements RetreatApplyService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private RetreatApplyFunc retreatApplyFunc;
    @Resource
    private RetreatApplyMapper retreatApplyMapper;
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageRetreatApplyByKey(PageRetreatApplyQuery query) {
        // 根据搜索关键字查询退住申请信息
        List<PageRetreatByKeyVo> pageRetreatByKeyVoList = retreatApplyMapper.listRetreatApplyByKey(query);
        // 封装返回数据
        PageResult<PageRetreatByKeyVo> pageResult = pageUtil.packPageResultData(pageRetreatByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Collections.singletonList(CheckEnum.ENTER.getStatus()));
        // 根据姓名和联系电话获取入住和退住审核老人列表
        List<PageSearchElderByKeyVo> pageSearchElderByKeyVoList = commonFunc.listPageElderByKey(query, checkFlagList);
        // 获取未审核的退住申请
        List<RetreatApply> retreatApplyList = retreatApplyFunc.listNotAuditApply();
        // 根据退住申请列表过滤老人
        pageSearchElderByKeyVoList = pageSearchElderByKeyVoList.stream().filter(pageSearchElderByKeyVo -> {
            AtomicBoolean existFlag = new AtomicBoolean(false);
            retreatApplyList.forEach(outward -> {
                if (Objects.equals(outward.getElderId(), pageSearchElderByKeyVo.getId())) {
                    existFlag.set(true);
                }
            });
            return !existFlag.get();
        }).collect(Collectors.toList());
        // 封装返回数据
        PageResult<PageSearchElderByKeyVo> pageResult = pageUtil.packPageResultData(pageSearchElderByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    @Transactional
    public Result addRetreatApply(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 验证老人是否为入住状态
        AssertUtil.isTrue(Objects.equals(elder.getCheckFlag(), CheckEnum.ENTER.getStatus()), ExceptionEnum.ELDER_NOT_ENTER);
        // 初始化退住申请
        RetreatApply retreatApply = new RetreatApply();
        retreatApply.setElderId(elderId);
        retreatApply.setApplyFlag(AuditEnum.STAY_AUDIT.getStatus());
        // 新增
        retreatApplyMapper.insert(retreatApply);
        // 封装老人修改
        elder.setCheckFlag(CheckEnum.EXIT_AUDIT.getStatus());
        // 修改
        elderMapper.updateById(elder);
        // 封装床位修改
        Bed bed = new Bed();
        bed.setId(elder.getBedId());
        bed.setBedFlag(BedEnum.EXIT_AUDIT.getStatus());
        // 修改
        bedMapper.updateById(bed);
        return Result.success();
    }
}
