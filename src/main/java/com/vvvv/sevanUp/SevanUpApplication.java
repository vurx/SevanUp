package com.vvvv.sevanUp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.vvvv.sevanUp.mapper")
public class SevanUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevanUpApplication.class, args);
    }

}
