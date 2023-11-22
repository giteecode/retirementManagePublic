package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.query.PageDepositRechargeByKeyQuery;
import com.ew.gerocomium.dao.query.PageSearchElderByKeyQuery;
import com.ew.gerocomium.dao.query.RechargeQuery;
import com.ew.gerocomium.dao.vo.PageDepositRechargeByKeyVo;
import com.ew.gerocomium.dao.vo.PageSearchElderByKeyVo;
import com.ew.gerocomium.service.DepositRechargeService;
import com.ew.gerocomium.service.common.CommonFunc;
import com.ew.gerocomium.service.common.ElderFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DepositRechargeServiceImpl implements DepositRechargeService {
    @Resource
    private CommonFunc commonFunc;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageDepositRechargeByKey(PageDepositRechargeByKeyQuery query) {
        // 根据搜索关键字查询预存充值信息
        List<PageDepositRechargeByKeyVo> pageDepositRechargeByKeyVoList = elderMapper.listDepositRechargeByKey(query);
        // 封装返回数据
        PageResult<PageDepositRechargeByKeyVo> pageResult = pageUtil.packPageResultData(pageDepositRechargeByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result pageSearchElderByKey(PageSearchElderByKeyQuery query) {
        List<String> checkFlagList = new ArrayList<>(Arrays.asList(CheckEnum.ENTER.getStatus(), CheckEnum.EXIT_AUDIT.getStatus()));
        // 根据姓名和联系电话获取入住和退住审核老人列表
        return commonFunc.pageSearchElderByKeyResult(query,checkFlagList);
    }

    @Override
    public Result recharge(RechargeQuery query) {
        // 判断是否是入住老人
        Elder elder = elderMapper.selectById(query.getElderId());
        boolean checkFlag = Objects.equals(elder.getCheckFlag(), CheckEnum.ENTER.getStatus()) ||
                Objects.equals(elder.getCheckFlag(), CheckEnum.EXIT_AUDIT.getStatus());
        AssertUtil.isTrue(checkFlag, ExceptionEnum.NOT_ENTER);
        // 封装修改
        elder.setBalance(query.getAmount().add(elder.getBalance()));
        // 修改
        elderMapper.updateById(elder);
        return Result.success();
    }
}
