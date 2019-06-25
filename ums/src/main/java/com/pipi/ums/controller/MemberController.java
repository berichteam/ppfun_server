package com.pipi.ums.controller;

import com.pipi.common.domain.Result;
import com.pipi.common.domain.UserMemberShip;
import com.pipi.common.domain.Users;
import com.pipi.common.enums.ResultCode;
import com.pipi.common.enums.SocialType;
import com.pipi.common.service.inter.MemberShipService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/6/25
 * @desc
 **/
@RestController
@CommonsLog
public class MemberController {

    @Autowired
    private MemberShipService memberShipService;

    /**
     * 会员充值
     * @param payment
     * @param params
     * @param request
     * @return
     */
    @PostMapping("/membership/deposit/{payment}")
    public Result deposit(@PathVariable String payment,
                          @RequestBody Map<String, String> params,
                          HttpServletRequest request) {
        if (!payment.equals("wechat")) {
            return Result.failure(ResultCode.FAILURE, "暂不支持该渠道登录");
        }
        Users user = (Users)request.getAttribute("user");
        if (user == null) {
            return Result.failure(ResultCode.FAILURE);
        }
        Integer type = Integer.valueOf(params.get("type"));
        Integer amount = Integer.valueOf(params.get("amount"));
        Long orderId = memberShipService.buyMemberShipAndPreOrder(type, amount, user.getId(), SocialType.WECHAT);
        log.info("会员充值，orderId: " + orderId);
        Map<String, Object> res = new HashMap<>();
        res.put("order_id", orderId);
        return Result.success(ResultCode.SUCCESS, res);
    }

    /**
     * 查询会员信息
     * @param request
     * @return
     */
    @GetMapping("/membership")
    public Result findMember(HttpServletRequest request) {
        Users user = (Users)request.getAttribute("user");
        if (user == null) {
            return Result.failure(ResultCode.FAILURE);
        }
        UserMemberShip memberShip = memberShipService.findByUser(user.getId());
        if (memberShip == null) {
            return Result.failure(ResultCode.FAILURE);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("firstDepositTime", memberShip.getFirstDepositTime());
        res.put("lastDepositTime", memberShip.getLastDepositTime());
        res.put("deadline", memberShip.getDeadline());
        res.put("grade", memberShip.getMemberGrade());
        res.put("type", memberShipService.findByTypeId(memberShip.getTypeId()).getMemberType());
        return Result.success(ResultCode.SUCCESS, res);
    }

}
