package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.EmergencyContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.ExpireContractEmergencyContactVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 紧急联系人表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface EmergencyContactMapper extends BaseMapper<EmergencyContact> {
    /**
     * 根据老人编号列表获取紧急联系人列表
     *
     * @param elderIdList
     * @return
     */
    List<ExpireContractEmergencyContactVo> listExpireContractEmergencyContactVoByElderIdList(@Param("elderIdList") List<Long> elderIdList);
}
