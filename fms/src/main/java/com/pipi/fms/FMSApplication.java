package com.pipi.fms;

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
public class FMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FMSApplication.class, args);
    }

}
