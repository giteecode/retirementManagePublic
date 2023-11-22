package com.ew.gerocomium.service.common;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AesUtil;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.DateUtilWen;
import com.ew.gerocomium.dao.mapper.StaffMapper;
import com.ew.gerocomium.dao.po.Consult;
import com.ew.gerocomium.dao.po.Contract;
import com.ew.gerocomium.dao.po.Staff;
import com.ew.gerocomium.dao.vo.BusinessTrendVo;
import com.ew.gerocomium.dao.vo.MonthPerformanceRankVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工表公共方法
 */
@Component
public class StaffFunc {
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private ConsultFunc consultFunc;
    @Resource
    private ContractFunc contractFunc;

    /**
     * 根据账号查询可用账号
     *
     * @param account
     * @return
     */
    public Staff getStaffByAccount(String account) {
        return staffMapper.selectOne(new LambdaQueryWrapper<Staff>()
                .and(staffLambdaQueryWrapper ->
                        staffLambdaQueryWrapper
                                .eq(Staff::getPhone, account).or()
                                .eq(Staff::getEmail, account)
                )
                .and(staffLambdaQueryWrapper ->
                        staffLambdaQueryWrapper
                                .eq(Staff::getLeaveFlag, YesNoEnum.NO.getCode())
                )
        );
    }

    /**
     * 忘记密码时验证账号信息
     *
     * @param account
     * @return
     */
    public Staff forgetCheckAccountAndPass(String account, String pass) {
        // 获取需修改密码的账号
        Staff staff = getStaffByAccount(account);
        // 账号未注册
        AssertUtil.notNull(staff, ExceptionEnum.ACCOUNT_NOT_REGISTER);
        // 新密码与原密码相同
        boolean checkPass = Objects.equals(AesUtil.aesEncode(pass), staff.getPass());
        AssertUtil.notTrue(checkPass, ExceptionEnum.PASS_SAME);
        // 返回数据
        return staff;
    }

    /**
     * 根据角色编号查询员工列表
     *
     * @param roleId
     * @return
     */
    public List<Staff> listStaffByRoleId(Long roleId) {
        return staffMapper.selectList(new LambdaQueryWrapper<Staff>()
                .and(staffLambdaQueryWrapper -> staffLambdaQueryWrapper
                        .eq(Staff::getRoleId, roleId)
                        .or().eq(Staff::getRoleId, 1L)
                )
                .eq(Staff::getLeaveFlag, YesNoEnum.NO.getCode()));
    }

    /**
     * 根据关键字查询员工列表
     *
     * @param roleId
     * @param name
     * @param sex
     * @return
     */
    public List<Staff> listStaffByKey(Long roleId, String name, String sex) {
        return staffMapper.selectList(new LambdaQueryWrapper<Staff>()
                .and(staffLambdaQueryWrapper -> {
                    staffLambdaQueryWrapper.eq(Staff::getLeaveFlag, YesNoEnum.NO.getCode());
                    if (ObjUtil.isNotEmpty(roleId)) {
                        staffLambdaQueryWrapper.eq(Staff::getRoleId, roleId);
                    }
                    if (ObjUtil.isNotEmpty(name)) {
                        staffLambdaQueryWrapper.like(Staff::getName, name);
                    }
                    if (ObjUtil.isNotEmpty(sex)) {
                        staffLambdaQueryWrapper.eq(Staff::getSex, sex);
                    }
                })
        );
    }

    /**
     * 获取业务员销售排行
     *
     * @param contractList
     * @return
     */
    public List<MonthPerformanceRankVo.SaleRank> listSaleRank(List<Consult> consultList, List<Contract> contractList) {
        // 根据员工编号对咨询进行分组
        Map<Long, List<Consult>> mapConsultByStaffId = consultFunc.mapConsultByStaffId(consultList);
        // 根据员工编号对合同进行分组
        Map<Long, List<Contract>> mapContractByStaffId = contractFunc.mapContractByStaffId(contractList);
        // 获取销售管理员列表
        List<Staff> staffList = listStaffByRoleId(2L);
        // 封装返回数据
        List<MonthPerformanceRankVo.SaleRank> saleRankList = new ArrayList<>();
        for (Staff staff : staffList) {
            MonthPerformanceRankVo.SaleRank saleRank = new MonthPerformanceRankVo.SaleRank();
            long consultNum = 0;
            long contractNum = 0;
            // 获取该员工咨询数
            for (Long staffId : mapConsultByStaffId.keySet()) {
                if (Objects.equals(staff.getId(), staffId)) {
                    consultNum = mapConsultByStaffId.get(staffId).size();
                    break;
                }
            }
            // 获取该员工合同数
            for (Long staffId : mapContractByStaffId.keySet()) {
                if (Objects.equals(staff.getId(), staffId)) {
                    contractNum = mapContractByStaffId.get(staffId).size();
                    break;
                }
            }
            saleRank.setName(staff.getName());
            saleRank.setConsultNum(consultNum);
            saleRank.setContractNum(contractNum);
            saleRankList.add(saleRank);
        }
        // 排序
        saleRankList.sort(Comparator.comparing(MonthPerformanceRankVo.SaleRank::getContractNum).reversed());
        // 编序号
        Long[] rankNum = {1L};
        saleRankList = saleRankList.stream()
                .peek(saleRank -> saleRank.setRank(rankNum[0]++))
                .collect(Collectors.toList());
        return saleRankList;
    }

    /**
     * 获取今年业务趋势
     *
     * @param consultList
     * @param contractList
     * @param yearStartTime
     * @return
     */
    public List<BusinessTrendVo> listBusinessTrend(List<Consult> consultList, List<Contract> contractList, Date yearStartTime) {
        // 封装返回数据
        List<BusinessTrendVo> businessTrendVoList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            // 获取当前月份开始/结束时间
            Date startTime = DateUtilWen.getNowMonthAroundTime(yearStartTime, i);
            Date endTime = DateUtilWen.getMonthLastDay(startTime);
            // 获取本月咨询客户记录
            List<Consult> thisMonthConsultList = consultFunc.listConsultByTimePeriod(consultList, startTime, endTime);
            // 获取本月签订合同记录
            List<Contract> thisMonthContractList = contractFunc.listContractByTimePeriod(contractList, startTime, endTime);
            // 封装数据
            BusinessTrendVo businessTrendVo = new BusinessTrendVo();
            businessTrendVo.setMonth(DateUtilWen.getYearToMonthFormatDateStr(startTime, "-"));
            businessTrendVo.setConsultNum((long) thisMonthConsultList.size());
            businessTrendVo.setContractNum((long) thisMonthContractList.size());
            businessTrendVoList.add(businessTrendVo);
        }
        return businessTrendVoList;
    }
}
