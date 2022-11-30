package com.ypjiao.mettingfilm.backend_show_consumer.config;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule getIRule(){
        return new RoundRobinRule();
    }
//    @Bean
//    public IPing getIPing(){
//        return new PingUrl();
//    }
}
