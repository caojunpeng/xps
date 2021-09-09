package com.cao.xps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//(scanBasePackages = {"com.cao.xps"})

@SpringBootApplication(scanBasePackages = {"com.cao.xps"})
@MapperScan("com.cao.xps.service.**.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
