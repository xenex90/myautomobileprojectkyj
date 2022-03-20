package com.example.myautomobileprojectkyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.myautomobileprojectkyj.mapper")
public class MyautomobileprojectkyjApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyautomobileprojectkyjApplication.class, args);
    }

}
