package com.ypjiao.mettingfilm.backend_hall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.ypjiao.mettingfilm.**.dao")
@ComponentScan("com.ypjiao.mettingfilm")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BackendHallApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendHallApplication.class, args);
	}

}
