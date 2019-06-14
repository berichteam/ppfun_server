package com.pipi.ums.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.pipi.common.domain.CheckMsg;
import com.pipi.common.domain.Result;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.BizType;
import com.pipi.common.enums.ResultCode;
import com.pipi.common.enums.SocialType;
import com.pipi.common.service.inter.CheckMsgService;
import com.pipi.common.service.inter.UserService;
import com.pipi.ums.utils.JwtTokenUtils;
import com.pipi.ums.wxserver.WxMaConfiguration;
import lombok.extern.apachecommons.CommonsLog;
import me.chanjar.weixin.common.error.WxErrorException;
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
     * @param confirmPassword
     * @param smsCode
     * @param phone
     * @return
     */
    @PostMapping("/user/register")
    public Result register(@RequestParam String username, @RequestParam String password,
                           @RequestParam(name = "confirm_password") String confirmPassword,
                           @RequestParam(name = "sms_code") String smsCode, @RequestParam String phone) {
        if (!password.equals(confirmPassword)) {
            log.error("密码确认错误");
            return Result.failure(ResultCode.USER_CONFIRM_PASSWORD_ERROR);
        }
        if (!checkMsgService.checkCode(phone, smsCode, BizType.REGISTER)) {
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
     * 社交媒体登录
     * @param socialType
     * @param code
     * @return
     */
    @PostMapping("/user/login/social")
    public Result loginSocial(@RequestParam(name = "social_type") String socialType, @RequestParam String code) {
        if (!socialType.equals("wechat")) {
            return Result.failure(ResultCode.FAILURE, "暂不支持该渠道登录");
        }
        WxMaService wxService = WxMaConfiguration.getMaService();
        try {
            final WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info("session_key: " + session.getSessionKey());
            log.info("open_id: " + session.getOpenid());
            // 判断用户是否存在，不存在即注册，存在即返回token
            Users user = userService.loginBySocial(session.getOpenid(), SocialType.WECHAT);
            if (user == null) {
                user = userService.registerBySocial(session.getOpenid(), SocialType.WECHAT);
            }
            String token = "";
            try {
                token = JwtTokenUtils.createToken(user.getUserName());
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("用户生成token失败：" + user.getUserName());
            }
            Map<String, String> res = new HashMap<>();
            res.put("auth_token", token);
            return Result.success(ResultCode.SUCCESS, res);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return Result.success(ResultCode.FAILURE, e.getMessage());
        }
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
