package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetElderLabelByIdLabelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface LabelMapper extends BaseMapper<Label> {
    /**
     * 根据老人编号获取标签
     *
     * @param elderId
     * @return
     */
    List<GetElderLabelByIdLabelVo> listElderLabelById(@Param("elderId") Long elderId);
}
