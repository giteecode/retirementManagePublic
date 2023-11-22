package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.AuditEnum;
import com.ew.gerocomium.dao.mapper.RetreatApplyMapper;
import com.ew.gerocomium.dao.po.RetreatApply;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退住申请表公共方法
 */
@Component
public class RetreatApplyFunc {
    @Resource
    private RetreatApplyMapper retreatApplyMapper;

    /**
     * 获取未审核的退住申请
     *
     * @return
     */
    public List<RetreatApply> listNotAuditApply() {
        return retreatApplyMapper.selectList(new LambdaQueryWrapper<RetreatApply>()
                .eq(RetreatApply::getApplyFlag, AuditEnum.STAY_AUDIT.getStatus())
        );
    }
}
