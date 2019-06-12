package com.pipi.ums.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lazyb
 * @create 2019/6/12
 * @desc
 **/
@RestController
@CommonsLog
public class IndexController {

    @RequestMapping("/auth_error")
    public ResponseEntity authError() {
        return new ResponseEntity("Access Denied!", HttpStatus.FORBIDDEN);
    }

}
