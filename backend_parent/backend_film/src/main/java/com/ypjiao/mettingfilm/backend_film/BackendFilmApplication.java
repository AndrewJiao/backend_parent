package com.ypjiao.mettingfilm.backend_film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = {"com.ypjiao.mettingfilm.backend_film.**.dao"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class BackendFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFilmApplication.class, args);
    }

}
