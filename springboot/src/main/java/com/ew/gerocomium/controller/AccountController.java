package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.EditQuery;
import com.ew.gerocomium.dao.query.ForgetQuery;
import com.ew.gerocomium.dao.query.LoginQuery;
import com.ew.gerocomium.dao.query.SendCodeQuery;
import com.ew.gerocomium.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "账号管理")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result login(@ApiParam(value = "登录请求参数", required = true) @RequestBody LoginQuery loginQuery) {
        return accountService.login(loginQuery);
    }

    @GetMapping("/sendCode")
    @ApiOperation(value = "发送验证码", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result sendCode(@ApiParam(value = "发送验证码请求参数", required = true) SendCodeQuery sendCodeQuery) {
        return accountService.sendCode(sendCodeQuery);
    }

    @PutMapping("/forget")
    @ApiOperation(value = "忘记密码", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result forget(@ApiParam(value = "忘记密码请求参数", required = true) @RequestBody ForgetQuery forgetQuery) {
        return accountService.forget(forgetQuery);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "修改密码", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result edit(@ApiParam(value = "忘记密码请求参数", required = true) @RequestBody EditQuery editQuery,
                       @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accountService.edit(editQuery);
    }

    @DeleteMapping("/logout")
    @ApiOperation(value = "登出", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result logout(@ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return accountService.logout();
    }
}
