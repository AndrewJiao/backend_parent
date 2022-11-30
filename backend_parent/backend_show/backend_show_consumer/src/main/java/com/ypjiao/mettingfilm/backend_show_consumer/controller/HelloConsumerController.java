package com.ypjiao.mettingfilm.backend_show_consumer.controller;

import com.ypjiao.mettingfilm.backend_show_consumer.feign.FeignServiceAPI;
import com.ypjiao.mettingfilm.backend_show_consumer.service.HelloConsumerService;
import com.ypjiao.mettingfilm.backend_utils.pojoDemo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
public class HelloConsumerController {
    @Autowired
    HelloConsumerService service;
    @Resource
    FeignServiceAPI feignAPI;

    @RequestMapping(value = "/consumer/call_hello",method = RequestMethod.GET)
    public String callProvider(String message){
        String s = service.callProviderService(message);
        return s;
    }

    /**
     * 测试RequestParam
     * @param message
     * @return
     */
    @RequestMapping(value = "/consumer/feign_hello",method = RequestMethod.GET)
    public String feignProvider(@RequestParam String message){
        String s = feignAPI.sendString(message);
        return s;
    }
    @RequestMapping(value = "/consumer/feign_body_hello",method = RequestMethod.GET)
    public String feignPojoProvider( Person person){
        String s = feignAPI.sendPoJo(person);
        return s;
    }
    @RequestMapping(value = "/consumer/feign_header_hello/{var}",method = RequestMethod.GET)
    public String feignHeaderProvider(@RequestHeader(value = "test") String header, @PathVariable String var){
        String s = feignAPI.sendHeader(header,var);
        return s;
    }


}
