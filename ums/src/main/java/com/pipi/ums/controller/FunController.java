package com.pipi.ums.controller;

import com.pipi.common.domain.Result;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ryan
 * @create 2019/6/6
 * @desc
 **/
@RestController
@CommonsLog
public class FunController {


    @Autowired
    private FunService funService;

    @GetMapping(value = "/fun/funList")
    public Result funList(@RequestParam("andAuthority") Integer andAuthority, HttpServletRequest request) {
        funService.findAllByPageAndAuthority(1,1,10);
        return Result.success( funService.findAllByPageAndAuthority(andAuthority,1,10));
    }


    @PostMapping(value = "/fun/funPublish")
    public Result funPublish(@RequestBody FunVo funVo, HttpServletRequest request) {
        funService.funPublish(funVo);
        return Result.success(funVo);
    }


}
