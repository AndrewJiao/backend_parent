package com.mettingfilm.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class BackendZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendZuulApplication.class, args);
    }

}
