package com.example.myautomobileprojectkyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //Base_Entity에 있는 날짜 자동 입력 활성화
@MapperScan("com.example.myautomobileprojectkyj.mapper")
public class MyautomobileprojectkyjApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyautomobileprojectkyjApplication.class, args);
    }

}
