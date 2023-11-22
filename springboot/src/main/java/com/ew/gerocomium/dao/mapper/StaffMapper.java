package com.ew.gerocomium.dao.mapper;

import com.ew.gerocomium.dao.po.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ew.gerocomium.dao.query.PageStaffByKeyQuery;
import com.ew.gerocomium.dao.vo.PageStaffByKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
public interface StaffMapper extends BaseMapper<Staff> {
    /**
     * 根据关键字查询员工列表
     *
     * @param keyQuery
     * @return
     */
    List<PageStaffByKeyVo> listStaffByKey(@Param("keyQuery") PageStaffByKeyQuery keyQuery);
}
