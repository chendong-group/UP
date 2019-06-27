package com.lx.curriculum_vitae;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lx.curriculum_vitae.dao"})
public class CurriculumVitaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurriculumVitaeApplication.class, args);
    }

}
