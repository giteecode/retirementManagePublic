package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ew.gerocomium.dao.mapper.RoleMapper;
import com.ew.gerocomium.dao.po.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色公共方法
 */
@Component
public class RoleFunc {
    @Resource
    private RoleMapper roleMapper;

    /**
     * 获取除超管之外的角色
     *
     * @return
     */
    public List<Role> listNotSuperAdminRole() {
        return roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .ne(Role::getId, 1));
    }
}
