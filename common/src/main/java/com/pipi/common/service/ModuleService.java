package com.pipi.common.service;

import org.springframework.stereotype.Service;

/**
 * @author lazyb
 * @create 2019/5/20
 * @desc
 **/
@Service("moduleService")
public class ModuleService {

    public String fmsModule() {
        return "fms service running";
    }

    public String umsModule() {
        return "ums service running";
    }

}
