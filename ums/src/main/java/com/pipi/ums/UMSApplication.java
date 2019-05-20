package com.pipi.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lazyb
 * @create 2019/5/20
 * @desc
 **/
@SpringBootApplication
@ComponentScan("com.pipi")
@ServletComponentScan
public class UMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(UMSApplication.class, args);
    }

}
