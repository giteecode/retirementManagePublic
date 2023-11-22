package com.ew.gerocomium.service.impl;

import cn.hutool.core.util.ReUtil;
import com.ew.gerocomium.common.config.security.entity.LoginUserDetails;
import com.ew.gerocomium.common.config.security.handler.AuthorityAssert;
import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.util.*;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.StaffMapper;
import com.ew.gerocomium.dao.po.Staff;
import com.ew.gerocomium.dao.query.EditQuery;
import com.ew.gerocomium.dao.query.ForgetQuery;
import com.ew.gerocomium.dao.query.LoginQuery;
import com.ew.gerocomium.dao.query.SendCodeQuery;
import com.ew.gerocomium.dao.vo.LoginUserVo;
import com.ew.gerocomium.service.AccountService;
import com.ew.gerocomium.service.common.StaffFunc;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private AuthorityAssert authorityAssert;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private StaffFunc staffFunc;

    @Override
    public Result login(LoginQuery query) {
        // AuthenticationManager进行用户认证
        // 将登录数据封装为UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(query.getPhone(), query.getPass());
        // 将封装对象传入进行认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证通过，获取登录用户信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        LoginUserVo loginUserVo = loginUserDetails.getLoginUserVo();
        // 使用用户id生成jwt，并设置到登录用户信息中
        String idStr = loginUserVo.getId().toString();
        loginUserVo.setToken(JwtUtil.createJwt(idStr));
        // 用户id作为key，将登录用户信息存入redis
        redisUtil.setCacheObject(Constant.LOGIN_REDIS + idStr, loginUserVo, Constant.EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return Result.success(loginUserVo);
    }

    @Override
    public Result sendCode(SendCodeQuery query) {
        // 忘记密码时验证账号信息
        staffFunc.forgetCheckAccountAndPass(query.getAccount(), query.getPass());
        // 重复发送验证码
        AssertUtil.isNull(redisUtil.getCacheObject(query.getAccount()), ExceptionEnum.REPEAT_SEND_CODE);
        // 生成验证码
        int num = (int) ((Math.random() * 9 + 1) * (Math.pow(10, 5)));
        String code = String.valueOf(num);
        // 实例化返回数据
        Result result = Result.success();
        // 账号为邮箱
        if (ReUtil.isMatch(Constant.EMAIL_REGULAR, query.getAccount())) {
            // 发送邮件
            SendEmailUtil.sendEmail(
                    new ArrayList<>(Collections.singletonList(query.getAccount())),
                    "验证码：" + code
            );
            // 账号为手机号
        } else if (ReUtil.isMatch(Constant.PHONE_REGULAR, query.getAccount())) {
            // 因服务原因，无法发送，直接返回code
            result.setData(code);
            // 账号既不是邮箱也不是手机号
        } else {
            return Result.error(ExceptionEnum.ACCOUNT_FORMAT_ERROR);
        }
        // 将验证码存入redis，账号作为key
        redisUtil.setCacheObject(query.getAccount(), code, 60 * 1000L, TimeUnit.MILLISECONDS);
        return result;
    }

    @Override
    public Result forget(ForgetQuery query) {
        // 验证并获取忘记密码的账号信息
        Staff staff = staffFunc.forgetCheckAccountAndPass(query.getAccount(), query.getPass());
        // 通过账号取出code
        String code = redisUtil.getCacheObject(query.getAccount());
        // 验证码过期
        AssertUtil.notNull(code, ExceptionEnum.CODE_EXPIRE);
        // 验证码错误
        AssertUtil.isTrue(Objects.equals(code, query.getCode()), ExceptionEnum.CODE_ERROR);
        // 封装修改密码
        staff.setPass(AesUtil.aesEncode(query.getPass()));
        // 修改
        staffMapper.updateById(staff);
        // 删除redis该账号的验证码缓存
        redisUtil.deleteObject(query.getAccount());
        // 删除redis登录信息
        redisUtil.deleteObject(Constant.LOGIN_REDIS + staff.getId());
        return Result.success();
    }

    @Override
    public Result edit(EditQuery query) {
        // 获取登录用户信息
        LoginUserVo loginUserInfo = authorityAssert.getLoginUserInfo();
        // 原密码错误
        boolean checkOldPass = Objects.equals(AesUtil.aesEncode(query.getOldPass()), loginUserInfo.getPass());
        AssertUtil.isTrue(checkOldPass, ExceptionEnum.OLD_PASS_ERROR);
        // 新密码与原密码相同
        boolean checkPass = Objects.equals(AesUtil.aesEncode(query.getNewPass()), loginUserInfo.getPass());
        AssertUtil.notTrue(checkPass, ExceptionEnum.PASS_SAME);
        // 封装修改密码
        Staff staff = new Staff();
        staff.setId(loginUserInfo.getId());
        staff.setPass(AesUtil.aesEncode(query.getNewPass()));
        // 修改
        staffMapper.updateById(staff);
        // 删除redis登录信息
        redisUtil.deleteObject(Constant.LOGIN_REDIS + staff.getId());
        return Result.success();
    }

    @Override
    public Result logout() {
        // 删除redis中的值
        redisUtil.deleteObject(Constant.LOGIN_REDIS + authorityAssert.getLoginUserId());
        return Result.success();
    }
}
