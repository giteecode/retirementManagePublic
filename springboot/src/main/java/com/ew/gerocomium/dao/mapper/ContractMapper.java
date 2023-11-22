package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Contract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.vo.ExpireContractVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 合同表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface ContractMapper extends BaseMapper<Contract> {
    /**
     * 获取已过期和即将到期的合同
     *
     * @param endTime
     * @return
     */
    List<ExpireContractVo> listExpireContract(@Param("endTime") Date endTime);
}
