package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.EditQuery;
import com.ew.gerocomium.dao.query.ForgetQuery;
import com.ew.gerocomium.dao.query.LoginQuery;
import com.ew.gerocomium.dao.query.SendCodeQuery;

public interface AccountService {
    /**
     * 登录
     *
     * @param loginQuery
     * @return
     */
    Result login(LoginQuery loginQuery);

    /**
     * 发送验证码
     *
     * @param sendCodeQuery
     * @return
     */
    Result sendCode(SendCodeQuery sendCodeQuery);

    /**
     * 忘记密码
     *
     * @param forgetQuery
     * @return
     */
    Result forget(ForgetQuery forgetQuery);

    /**
     * 修改密码
     * @param editQuery
     * @return
     */
    Result edit(EditQuery editQuery);

    /**
     * 登出
     *
     * @return
     */
    Result logout();
}
