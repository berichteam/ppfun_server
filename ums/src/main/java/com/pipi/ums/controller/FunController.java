package com.pipi.ums.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipi.common.domain.Result;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    @PostMapping(value = "/article")
    public Result funPublish(@RequestBody FunVo funVo, HttpServletRequest request) {
        funService.funPublish(funVo);
        return Result.success(funVo);
    }

    @GetMapping(value = "/fun/funList")
    @ResponseBody
    public Result funList(@RequestParam("andAuthority") Integer andAuthority, HttpServletRequest request) {
        PageHelper.startPage(1, 4);
        List<FunDTO> list = funService.findAllByPageAndAuthority(andAuthority);
        PageInfo<FunDTO> pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }





}
