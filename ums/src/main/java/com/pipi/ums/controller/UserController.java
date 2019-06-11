package com.pipi.ums.controller;

import com.pipi.common.domain.CheckMsg;
import com.pipi.common.domain.Result;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.BizType;
import com.pipi.common.enums.ResultCode;
import com.pipi.common.service.inter.CheckMsgService;
import com.pipi.common.service.inter.UserService;
import com.pipi.ums.utils.JwtTokenUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
@RestController
@CommonsLog
public class UserController {

    @Autowired
    private CheckMsgService checkMsgService;
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param username
     * @param password
     * @param confirm_password
     * @param sms_code
     * @param phone
     * @return
     */
    @PostMapping("/user/register")
    public Result register(@RequestParam String username, @RequestParam String password, @RequestParam String confirm_password,
                           @RequestParam String sms_code, @RequestParam String phone) {
        if (!password.equals(confirm_password)) {
            log.error("密码确认错误");
            return Result.failure(ResultCode.USER_CONFIRM_PASSWORD_ERROR);
        }
        if (!checkMsgService.checkCode(phone, sms_code, BizType.REGISTER)) {
            log.error("验证码错误");
            return Result.failure(ResultCode.USER_CHECK_CODE_ERROR);
        }
        Users user = userService.registerByPhoneAndName(phone, username, password);
        if (user == null) {
            log.error("用户名或手机号错误");
            return Result.failure(ResultCode.USER_NAME_PHONE_REPEAT);
        }
        return Result.success(ResultCode.SUCCESS, user);
    }

    /**
     * 用户登录，成功后返回token
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/user/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        String token = "";
        Users user = userService.loginByName(username, password);
        if (user == null) {
            log.error("账户名或密码错误，登录失败");
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        try {
            token = JwtTokenUtils.createToken(username);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("用户生成token失败：" + username);
        }
        Map<String, String> res = new HashMap<>();
        res.put("auth_token", token);
        return Result.success(ResultCode.SUCCESS, res);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @PostMapping("/user/code")
    public Result sendCode(@RequestParam String phone) {
        CheckMsg ck = checkMsgService.addOneCode(phone, BizType.REGISTER);
        if (ck != null) {
            log.info("sms code: " + ck.getCode());
            // TODO 这里需要加入短信发送的逻辑
            return Result.success(ResultCode.SUCCESS);
        } else {
            return Result.failure(ResultCode.FAILURE);
        }
    }

}
