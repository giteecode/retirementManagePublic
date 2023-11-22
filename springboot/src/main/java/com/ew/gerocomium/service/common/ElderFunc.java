package com.ew.gerocomium.service.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.common.constant.CheckEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.query.OperateConsultQuery;
import com.ew.gerocomium.dao.query.AddReserveQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 老人表公共方法
 */
@Component
public class ElderFunc extends ServiceImpl<ElderMapper, Elder> {
    @Resource
    private ElderMapper elderMapper;

    /**
     * 根据身份证号获取老人信息
     *
     * @param idNum
     * @return
     */
    public Elder getElderByIdNum(String idNum) {
        return elderMapper.selectOne(new LambdaQueryWrapper<Elder>()
                .eq(Elder::getIdNum, idNum)
                .ne(Elder::getCheckFlag, CheckEnum.FAILURE.getStatus()));
    }

    /**
     * 验证老人
     *
     * @param elderId
     * @param idNum
     * @return
     */
    public Elder checkElder(Long elderId, String idNum) {
        // 验证是否需要验证身份证号/验证是否是删除状态
        boolean checkIdNum = ObjUtil.isNotEmpty(idNum);
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 若老人不存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 若老人已被删除
        boolean checkDel = Objects.equals(elder.getCheckFlag(), CheckEnum.FAILURE.getStatus());
        AssertUtil.notTrue(checkDel, !checkIdNum ? ExceptionEnum.DEL_REPEAT : ExceptionEnum.ELDER_ALREADY_DELETE);
        // 身份证号不为空 && 身份证号不一致
        if (checkIdNum && !Objects.equals(elder.getIdNum(), idNum)) {
            // 验证老人身份证号是否已存在
            AssertUtil.isNull(getElderByIdNum(idNum), ExceptionEnum.ID_NUM_REPEAT);
        }
        return elder;
    }

    /**
     * 根据条件获取老人信息
     *
     * @param elderName
     * @param elderPhone
     * @return
     */
    public List<Elder> listElderByKey(String elderName, String elderPhone) {
        return elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    if (ObjUtil.isNotEmpty(elderName)) {
                        elderLambdaQueryWrapper.like(Elder::getName, elderName);
                    }
                    if (ObjUtil.isNotEmpty(elderPhone)) {
                        elderLambdaQueryWrapper.like(Elder::getPhone, elderPhone);
                    }
                })
        );
    }

    /**
     * 根据条件获取老人信息
     *
     * @param elderName
     * @param elderPhone
     * @param checkFlag
     * @return
     */
    public List<Elder> listElderByKey(String elderName, String elderPhone, String checkFlag) {
        return elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.eq(Elder::getCheckFlag, checkFlag);
                    if (ObjUtil.isNotEmpty(elderName)) {
                        elderLambdaQueryWrapper.like(Elder::getName, elderName);
                    }
                    if (ObjUtil.isNotEmpty(elderPhone)) {
                        elderLambdaQueryWrapper.like(Elder::getPhone, elderPhone);
                    }
                })
        );
    }

    /**
     * 根据条件获取老人信息
     *
     * @param elderName
     * @param elderPhone
     * @param checkFlagList
     * @return
     */
    public List<Elder> listElderByKey(String elderName, String elderPhone, List<String> checkFlagList) {
        return elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    if (ObjUtil.isNotEmpty(elderName)) {
                        elderLambdaQueryWrapper.like(Elder::getName, elderName);
                    }
                    if (ObjUtil.isNotEmpty(elderPhone)) {
                        elderLambdaQueryWrapper.like(Elder::getPhone, elderPhone);
                    }
                })
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    checkFlagList.forEach(checkFlag ->
                            elderLambdaQueryWrapper.eq(Elder::getCheckFlag, checkFlag).or()
                    );
                })
        );
    }

    /**
     * 根据条件获取老人信息
     *
     * @param elderName
     * @param elderSex
     * @param idNum
     * @param checkFlagList
     * @return
     */
    public List<Elder> listElderByKey(String elderName, String elderSex, String idNum, List<String> checkFlagList) {
        return elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    if (ObjUtil.isNotEmpty(elderName)) {
                        elderLambdaQueryWrapper.like(Elder::getName, elderName);
                    }
                    if (ObjUtil.isNotEmpty(elderSex)) {
                        elderLambdaQueryWrapper.like(Elder::getSex, elderSex);
                    }
                    if (ObjUtil.isNotEmpty(idNum)) {
                        elderLambdaQueryWrapper.like(Elder::getIdNum, idNum);
                    }
                })
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    checkFlagList.forEach(checkFlag ->
                            elderLambdaQueryWrapper.eq(Elder::getCheckFlag, checkFlag).or()
                    );
                })
        );
    }

    /**
     * 根据编号过滤老人信息
     *
     * @param elderList
     * @param elderId
     * @return
     */
    public Elder filterElderById(List<Elder> elderList, Long elderId) {
        List<Elder> collect = elderList.stream()
                .filter(elder -> Objects.equals(elder.getId(), elderId))
                .collect(Collectors.toList());
        return collect.size() == 0 ? null : collect.get(0);
    }

    /**
     * 操作咨询时初始化老人
     *
     * @param operateConsultQuery
     * @param addFlag
     * @return
     */
    public Elder operateConsultInitElder(OperateConsultQuery operateConsultQuery, Boolean addFlag) {
        Elder elder = BeanUtil.toBean(operateConsultQuery, Elder.class);
        elder.setName(operateConsultQuery.getElderName());
        elder.setPhone(operateConsultQuery.getElderPhone());
        if (addFlag) {
            elder.setBalance(BigDecimal.valueOf(0));
        }
        elder.setCheckFlag(CheckEnum.CONSULT.getStatus());
        return elder;
    }

    /**
     * 操作预定时初始化老人
     *
     * @param addReserveQuery
     * @param addFlag
     * @return
     */
    public Elder operateReserveInitElder(AddReserveQuery addReserveQuery, Boolean addFlag) {
        Elder elder = new Elder();
        elder.setId(null);
        elder.setBedId(addReserveQuery.getBedId());
        elder.setName(addReserveQuery.getElderName());
        elder.setIdNum(addReserveQuery.getIdNum());
        elder.setAge(addReserveQuery.getElderAge());
        elder.setSex(addReserveQuery.getElderSex());
        elder.setPhone(addReserveQuery.getElderPhone());
        elder.setAddress(addReserveQuery.getElderAddress());
        if (addFlag) {
            elder.setBalance(BigDecimal.valueOf(0));
        }
        elder.setCheckFlag(CheckEnum.RESERVE.getStatus());
        return elder;
    }

    /**
     * 根据老人姓名获取所有占用房间的老人
     *
     * @param elderName
     * @return
     */
    public List<Elder> listLiveElder(String elderName) {
        return elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper -> {
                    elderLambdaQueryWrapper.isNotNull(Elder::getId);
                    if (ObjUtil.isNotEmpty(elderName)) {
                        elderLambdaQueryWrapper.like(Elder::getName, elderName);
                    }
                })
                .and(elderLambdaQueryWrapper ->
                        elderLambdaQueryWrapper
                                .eq(Elder::getCheckFlag, CheckEnum.RESERVE.getStatus()).or()
                                .eq(Elder::getCheckFlag, CheckEnum.ENTER.getStatus()).or()
                                .eq(Elder::getCheckFlag, CheckEnum.EXIT_AUDIT.getStatus())
                )
        );
    }

    /**
     * 判断该老人状态是否是意向客户
     *
     * @param elder
     */
    public void checkIntention(Elder elder) {
        boolean checkIntention = Objects.equals(elder.getCheckFlag(), CheckEnum.INTENTION.getStatus());
        AssertUtil.notTrue(checkIntention, ExceptionEnum.ALREADY_INTENTION);
        checkReserve(elder);
    }

    /**
     * 判断该老人状态是否是预定
     *
     * @param elder
     */
    public void checkReserve(Elder elder) {
        boolean checkIntention = Objects.equals(elder.getCheckFlag(), CheckEnum.RESERVE.getStatus());
        AssertUtil.notTrue(checkIntention, ExceptionEnum.ALREADY_RESERVE);
        checkEnterOrExitAudit(elder);
    }

    /**
     * 判断该老人状态是否是入住或退住审核
     *
     * @param elder
     */
    public void checkEnterOrExitAudit(Elder elder) {
        boolean checkEnter = Objects.equals(elder.getCheckFlag(), CheckEnum.ENTER.getStatus()) ||
                Objects.equals(elder.getCheckFlag(), CheckEnum.EXIT_AUDIT.getStatus());
        AssertUtil.notTrue(checkEnter, ExceptionEnum.ALREADY_ENTER);
    }

    /**
     * 判断是否有入住老人选择该护理等级
     *
     * @param nurseGradeId
     */
    public void checkNurse(Long nurseGradeId) {
        List<Elder> elderList = elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper ->
                        elderLambdaQueryWrapper
                                .eq(Elder::getNursingGradeId, nurseGradeId))
                .and(elderLambdaQueryWrapper ->
                        elderLambdaQueryWrapper
                                .eq(Elder::getCheckFlag, CheckEnum.ENTER.getStatus()).or()
                                .eq(Elder::getCheckFlag, CheckEnum.EXIT_AUDIT.getStatus()))
        );
        AssertUtil.isEmpty(elderList, ExceptionEnum.NURSE_GRADE_SELECTED);
    }

    /**
     * 判断是否有入住老人选择该餐饮套餐
     *
     * @param cateringSetId
     */
    public void checkCatering(Long cateringSetId) {
        List<Elder> elderList = elderMapper.selectList(new LambdaQueryWrapper<Elder>()
                .and(elderLambdaQueryWrapper ->
                        elderLambdaQueryWrapper
                                .eq(Elder::getCateringSetId, cateringSetId))
                .and(elderLambdaQueryWrapper ->
                        elderLambdaQueryWrapper
                                .eq(Elder::getCheckFlag, CheckEnum.ENTER.getStatus()).or()
                                .eq(Elder::getCheckFlag, CheckEnum.EXIT_AUDIT.getStatus()))
        );
        AssertUtil.isEmpty(elderList, ExceptionEnum.SET_SELECTED);
    }

    /**
     * 老人扣费
     *
     * @param elderId
     * @param payAmount
     */
    public void deductionFee(Long elderId, BigDecimal payAmount) {
        // 根据编号获取老人信息
        Elder elder = elderMapper.selectById(elderId);
        // 判断老人是否余额充足
        boolean checkBalance = elder.getBalance().compareTo(payAmount) < 0;
        AssertUtil.notTrue(checkBalance, ExceptionEnum.BALANCE_DEFICIENCY);
        // 初始化老人修改
        elder.setBalance(elder.getBalance().subtract(payAmount));
        // 修改老人
        elderMapper.updateById(elder);
    }
}
