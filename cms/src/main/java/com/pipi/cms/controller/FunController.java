package com.pipi.cms.controller;

import com.pipi.common.domain.Fun;
import com.pipi.common.service.inter.FunService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc    文章
 **/
@RestController
@CommonsLog
public class FunController {

    @Autowired
    private FunService funService;

    @GetMapping("/fun/list")
    public Map<String, Object> getFunList(int page, int rows) {
        Map<String, Object> resMap = new HashMap<>();
        Page<Fun> pu = funService.findAllByPage(page, rows);
        resMap.put("total", pu.getTotalElements());
        resMap.put("rows", pu.getContent());
        return resMap;
    }

}
