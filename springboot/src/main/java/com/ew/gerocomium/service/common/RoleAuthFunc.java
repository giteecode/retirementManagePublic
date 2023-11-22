package com.ew.gerocomium.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ew.gerocomium.dao.mapper.RoleAuthMapper;
import com.ew.gerocomium.dao.po.RoleAuth;
import org.springframework.stereotype.Component;

/**
 * 角色权限表公共方法
 */
@Component
public class RoleAuthFunc extends ServiceImpl<RoleAuthMapper, RoleAuth> {
}
