package com.ypjiao.mettingfilm.backend_show_consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
public class HelloConsumerServiceImpl implements HelloConsumerService {
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    LoadBalancerClient client;
    @Override
    public String callProviderService(String mesage) {
//        String port = "7201";
//        String host = "localhost";

        //动态获取 loadbalance
//        ServiceInstance provider = client.choose("hello_eureka_provider");
//        int port = provider.getPort();
//        String host = provider.getHost();
//        String url = "http://"+host + ":" + port +"/provider/provid_hello?message="+mesage;

        //通过LoadBalance注解实现  服务名称不能用下划线
        String appName = "HELLO-EUREKA-PROVIDER";
        String url = "http://"+appName+"/provider/provid_hello?message="+mesage;
        log.error("Return url = {}",url);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
