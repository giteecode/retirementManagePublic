package com.ew.gerocomium.service.common;

import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.VisitMapper;
import com.ew.gerocomium.dao.po.Visit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 来访登记表公共方法
 */
@Component
public class VisitFunc {
    @Resource
    private VisitMapper visitMapper;

    /**
     * 验证来访登记
     *
     * @param visitId
     * @param delFlag
     * @return
     */
    public Visit checkVisit(Long visitId, Boolean delFlag) {
        // 根据编号获取来访登记
        Visit visit = visitMapper.selectById(visitId);
        // 验证来访登记是否存在
        AssertUtil.notNull(visit, ExceptionEnum.DATA_NOT_EXIST);
        // 验证来访登记是否被删除
        boolean checkDel = Objects.equals(visit.getDelFlag(), YesNoEnum.YES.getCode());
        AssertUtil.notTrue(checkDel, delFlag ? ExceptionEnum.DEL_REPEAT : ExceptionEnum.RECORD_ALREADY_DELETE);
        // 非删除
        if (!delFlag) {
            // 验证是否已登记离开
            AssertUtil.isNull(visit.getLeaveDate(), ExceptionEnum.ALREADY_LEAVE);
        }
        return visit;
    }
}
