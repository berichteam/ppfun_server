package com.pipi.ums.controller;

import com.pipi.common.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lazyb
 * @create 2019/5/20
 * @desc
 **/
@RestController
public class IndexController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/")
    public String index() {
        return moduleService.umsModule();
    }

}
