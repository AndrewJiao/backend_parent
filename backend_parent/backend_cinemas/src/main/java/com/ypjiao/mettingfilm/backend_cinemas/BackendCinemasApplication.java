package com.ypjiao.mettingfilm.backend_cinemas;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ypjiao.mettingfilm.backend_cinemas.**.dao"})
@SpringBootApplication
public class BackendCinemasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCinemasApplication.class, args);
	}

}
