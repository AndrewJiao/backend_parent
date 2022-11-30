package com.ypjiao.mettingfilm.backend_show_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BackendShowProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowProviderApplication.class, args);
    }

}
