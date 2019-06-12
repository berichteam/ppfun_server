package com.pipi.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lazyb
 * @create 2019/5/20
 * @desc
 **/
@SpringBootApplication
@ComponentScan("com.pipi")
@MapperScan(value = "com.pipi.common.persistence.mapper")
@ServletComponentScan
@EntityScan("com.pipi.common.domain")
public class UMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(UMSApplication.class, args);
    }

}
