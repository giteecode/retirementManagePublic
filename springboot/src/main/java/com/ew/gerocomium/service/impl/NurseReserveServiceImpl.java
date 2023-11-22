package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.ChargeEnum;
import com.ew.gerocomium.common.constant.ConsumeEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.NurseReserveMapper;
import com.ew.gerocomium.dao.po.NurseReserve;
import com.ew.gerocomium.dao.po.ServiceItem;
import com.ew.gerocomium.dao.query.AddNurseReserveQuery;
import com.ew.gerocomium.dao.query.ExecuteNurseReserveQuery;
import com.ew.gerocomium.dao.query.PageNurseReserveByKeyQuery;
import com.ew.gerocomium.dao.vo.PageNurseReserveByKeyVo;
import com.ew.gerocomium.service.NurseReserveService;
import com.ew.gerocomium.service.common.ConsumeFunc;
import com.ew.gerocomium.service.common.ElderFunc;
import com.ew.gerocomium.service.common.ServiceItemFunc;
import com.ew.gerocomium.service.common.StaffFunc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class NurseReserveServiceImpl implements NurseReserveService {
    @Resource
    private ServiceItemFunc serviceItemFunc;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private NurseReserveMapper nurseReserveMapper;
    @Resource
    private ElderFunc elderFunc;
    @Resource
    private ConsumeFunc consumeFunc;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageNurseReserveByKey(PageNurseReserveByKeyQuery query) {
        // 根据关键词获取护理预定列表
        List<PageNurseReserveByKeyVo> pageNurseReserveByKeyVoList = nurseReserveMapper.listNurseReserveByKey(query);
        // 封装返回数据
        PageResult<PageNurseReserveByKeyVo> pageResult = pageUtil.packPageResultData(pageNurseReserveByKeyVoList, query.getPageNum(), query.getPageSize());
        // 替换订单状态
        pageResult.getList().forEach(pageNurseReserveByKeyVo -> pageNurseReserveByKeyVo.setOrderFlag(
                Objects.equals(pageNurseReserveByKeyVo.getOrderFlag(), YesNoEnum.NO.getCode()) ?
                        "待支付" :
                        "已完成"
        ));
        return Result.success(pageResult);
    }

    @Override
    public Result listService() {
        // 根据搜索关键字查询服务项目
        List<ServiceItem> listNotDelServiceItem = serviceItemFunc.listNotDelServiceItemByKey(null, null, ChargeEnum.ONCE.getMethod());
        return Result.success(BeanUtil.copyToList(listNotDelServiceItem, DropDown.class));
    }

    @Override
    public Result addNurseReserve(AddNurseReserveQuery query) {
        // 初始化护理预定
        NurseReserve nurseReserve = BeanUtil.toBean(query, NurseReserve.class);
        nurseReserve.setOrderFlag(YesNoEnum.NO.getCode());
        // 新增
        nurseReserveMapper.insert(nurseReserve);
        return Result.success();
    }

    @Override
    public Result listNurseStaff() {
        return Result.success(BeanUtil.copyToList(staffFunc.listStaffByRoleId(5L), DropDown.class));
    }

    @Override
    @Transactional
    public Result executeNurseReserve(ExecuteNurseReserveQuery query) {
        // 根据编号获取护理预定
        NurseReserve getNurseReserveById = nurseReserveMapper.selectById(query.getId());
        // 判断订单是否已完成
        boolean checkOrderFlag = ObjUtil.isNotEmpty(getNurseReserveById.getStaffId()) ||
                ObjUtil.isNotEmpty(getNurseReserveById.getNurseDate()) ||
                Objects.equals(getNurseReserveById.getOrderFlag(), YesNoEnum.YES.getCode());
        AssertUtil.notTrue(checkOrderFlag, ExceptionEnum.ORDER_SUCCESS);
        // 封装护理预定修改
        NurseReserve nurseReserve = BeanUtil.toBean(query, NurseReserve.class);
        nurseReserve.setOrderFlag(YesNoEnum.YES.getCode());
        // 修改护理预定
        nurseReserveMapper.updateById(nurseReserve);
        // 老人扣费
        elderFunc.deductionFee(getNurseReserveById.getElderId(), getNurseReserveById.getPayAmount());
        // 新增消费记录
        consumeFunc.addConsume(getNurseReserveById.getElderId(), ConsumeEnum.NURSE.getType(), getNurseReserveById.getPayAmount(), nurseReserve.getNurseDate());
        return Result.success();
    }
}
