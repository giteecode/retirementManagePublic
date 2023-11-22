package com.ew.gerocomium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com/ew/gerocomium/dao/mapper")
@SpringBootApplication
public class GerocomiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerocomiumApplication.class, args);
    }

}
