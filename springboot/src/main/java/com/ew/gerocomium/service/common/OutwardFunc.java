package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.dao.mapper.OutwardMapper;
import com.ew.gerocomium.dao.po.Outward;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 外出登记表公共方法
 */
@Component
public class OutwardFunc {
    @Resource
    private OutwardMapper outwardMapper;

    /**
     * 验证外出登记
     *
     * @param outwardId
     * @param delFlag
     * @return
     */
    public Outward checkOutward(Long outwardId, Boolean delFlag) {
        // 根据编号获取外出登记
        Outward outward = outwardMapper.selectById(outwardId);
        // 验证外出登记是否存在
        AssertUtil.notNull(outward, ExceptionEnum.DATA_NOT_EXIST);
        // 验证是否已被删除
        boolean checkDel = Objects.equals(outward.getDelFlag(), YesNoEnum.YES.getCode());
        AssertUtil.notTrue(checkDel, delFlag ? ExceptionEnum.DEL_REPEAT : ExceptionEnum.RECORD_ALREADY_DELETE);
        return outward;
    }

    public List<Outward> listNotDelAndNotReturnOutward() {
        return outwardMapper.selectList(new LambdaQueryWrapper<Outward>()
                .isNull(Outward::getRealReturnDate)
                .eq(Outward::getDelFlag, YesNoEnum.NO.getCode())
        );
    }
}
