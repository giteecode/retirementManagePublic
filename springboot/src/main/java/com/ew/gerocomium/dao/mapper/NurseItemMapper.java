package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.NurseItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.GetNurseGradeByIdVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 护理项目表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface NurseItemMapper extends BaseMapper<NurseItem> {
    /**
     * 根据护理等级编号获取服务列表
     *
     * @param gradeId
     * @return
     */
    List<GetNurseGradeByIdVo.NurseGradeServiceVo> listGradeService(@Param("gradeId") Long gradeId);
}
