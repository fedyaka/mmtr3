package com.example.mmtr3;

import com.example.mmtr3.config.OnApplicationStartUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Mmtr2Application {

    public static void main(String[] args){
        SpringApplication.run(Mmtr2Application.class, args);
    }

}
