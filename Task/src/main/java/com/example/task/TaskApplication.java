package com.example.task;


import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication

public class TaskApplication {

    public static void main(String[] args){
        SpringApplication.run(TaskApplication.class, args);
    }



}
