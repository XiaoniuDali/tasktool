package com.bymz.tasktool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TasktoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasktoolApplication.class, args);
    }

}
