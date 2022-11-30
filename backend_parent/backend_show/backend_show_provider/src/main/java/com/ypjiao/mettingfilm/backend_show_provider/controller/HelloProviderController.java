package com.ypjiao.mettingfilm.backend_show_provider.controller;

import com.ypjiao.mettingfilm.backend_utils.pojoDemo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HelloProviderController {
    @Value("${server.port}")
    public int port;
    @RequestMapping(value = "/provider/provid_hello",method = RequestMethod.GET)
    private String provideHello(String message){
        log.error("provider say hello from port:{} message:{}",port,message);
        return "provider say hello from port:"+port+" message:"+message;
    }

    @RequestMapping(value = "/provider/privide_POJO_hello",method = RequestMethod.POST)
    private String provideHello(@RequestBody Person person){
        log.error("privide_POJO_hello say hello from port:{} person_name:{} person_id:{}",port,person.getName(),person.getId());
        return "privide_POJO_hello say hello from port:"+port+" person_name:"+person.getName() +" person_id:"+person.getId();
    }

    @RequestMapping(value = "/provider/privide_header_hello/{var}",method = RequestMethod.POST)
    private String provideHello(@RequestHeader String header,@PathVariable String var){
        log.error("privide_header_hello say hello from port:{} header:{} var:{}",port,header,var);
        return "privide_POJO_hello say hello from port:"+port+" header:"+header +" var:"+var;
    }
}
