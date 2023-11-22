package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AesUtil;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.common.util.RedisUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.StaffMapper;
import com.ew.gerocomium.dao.po.Staff;
import com.ew.gerocomium.dao.query.OperateStaffQuery;
import com.ew.gerocomium.dao.query.PageStaffByKeyQuery;
import com.ew.gerocomium.dao.vo.OperateStaffVo;
import com.ew.gerocomium.dao.vo.PageStaffByKeyVo;
import com.ew.gerocomium.service.StaffService;
import com.ew.gerocomium.service.common.RoleFunc;
import com.ew.gerocomium.service.common.StaffFunc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    private RoleFunc roleFunc;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private StaffFunc staffFunc;
    @Resource
    private PageUtil pageUtil;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Result getRole() {
        return Result.success(BeanUtil.copyToList(roleFunc.listNotSuperAdminRole(), DropDown.class));
    }

    @Override
    public Result pageStaffByKey(PageStaffByKeyQuery query) {
        // 根据关键字查询员工列表
        List<PageStaffByKeyVo> pageStaffByKeyVoList = staffMapper.listStaffByKey(query);
        // 封装返回数据
        PageResult<PageStaffByKeyVo> pageResult = pageUtil.packPageResultData(pageStaffByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result addStaff(OperateStaffQuery query) {
        // 验证手机号是否已被注册
        AssertUtil.isNull(staffFunc.getStaffByAccount(query.getPhone()), ExceptionEnum.PHONE_REPEAT);
        // 验证邮箱是否已被注册
        AssertUtil.isNull(staffFunc.getStaffByAccount(query.getEmail()), ExceptionEnum.EMAIL_REPEAT);
        // 初始化员工
        query.setId(null);
        Staff staff = BeanUtil.toBean(query, Staff.class);
        staff.setPass(AesUtil.aesEncode(query.getPhone().substring(5, 11)));
        staff.setLeaveFlag(YesNoEnum.NO.getCode());
        // 新增
        staffMapper.insert(staff);
        return Result.success();
    }

    @Override
    public Result getStaffById(Long staffId) {
        // 根据编号获取员工
        Staff staff = staffMapper.selectById(staffId);
        // 判断是否为空
        AssertUtil.notNull(staff, ExceptionEnum.DATA_NOT_EXIST);
        // 转换实体
        OperateStaffVo operateStaffVo = BeanUtil.toBean(staff, OperateStaffVo.class);
        return Result.success(operateStaffVo);
    }

    @Override
    public Result editStaff(OperateStaffQuery query) {
        // 验证手机号是否已被注册
        Staff staffByPhone = staffFunc.getStaffByAccount(query.getPhone());
        boolean checkPhone = staffByPhone != null && !Objects.equals(staffByPhone.getId(), query.getId());
        AssertUtil.notTrue(checkPhone, ExceptionEnum.PHONE_REPEAT);
        // 验证邮箱是否已被注册
        Staff staffByEmail = staffFunc.getStaffByAccount(query.getEmail());
        boolean checkEmail = staffByEmail != null && !Objects.equals(staffByEmail.getId(), query.getId());
        AssertUtil.notTrue(checkEmail, ExceptionEnum.EMAIL_REPEAT);
        // 封装修改
        Staff staff = BeanUtil.toBean(query, Staff.class);
        // 修改
        staffMapper.updateById(staff);
        return Result.success();
    }

    @Override
    public Result leaveStaff(Long staffId) {
        // 封装修改
        Staff staff = new Staff();
        staff.setId(staffId);
        staff.setLeaveFlag(YesNoEnum.YES.getCode());
        // 修改
        staffMapper.updateById(staff);
        // 删除该账号登录记录
        redisUtil.deleteObject(Constant.LOGIN_REDIS + staffId);
        return Result.success();
    }
}
