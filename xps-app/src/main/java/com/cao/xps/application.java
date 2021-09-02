package com.cao.xps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//scanBasePackages:service注入;scanBasePackages="com.cao.wps.service.**"

@SpringBootApplication
@MapperScan("com.cao.wps.**")
public class application {

    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
