package com.pipi.cms.controller;

import com.github.pagehelper.PageInfo;
import com.pipi.common.domain.Result;
import com.pipi.common.domain.Users;
import com.pipi.common.service.inter.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/5/30
 * @desc
 **/
@RestController
@CommonsLog
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/list")
    public Result getUserList(int page, int rows) {
        PageInfo<Users> pu = userService.findAllByPage(page, rows);
        return Result.success(pu);
    }

}
