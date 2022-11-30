package com.ypjiao.mettingfilm.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseRequestVo;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.JwtTokenUtil;
import com.ypjiao.mettingfilm.controller.vo.LoginReqVo;
import com.ypjiao.mettingfilm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户表现层模块
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("login")

    /**
     * 用户登录
     * 1. 验证登录参数
     * 2. 利用通用mapper的QueryWrapper.eq()函数根据用户名查询用户，验证密码，返回用户uuid
     * 3. 返回randomkey和用户token
     */
    public BaseResponseVo loginUser(@RequestBody LoginReqVo loginReqVo) throws CommonServiceException {
        //验证请求参数合法性
        loginReqVo.checkVoParam();
        //验证用户名
        String uuid = userService.login(loginReqVo.getUsername(), loginReqVo.getPassword());
        Map<String, String> result = new HashMap<>();
        JwtTokenUtil tokenUtil = new JwtTokenUtil();
        //获取混淆MD5签名用的随机字符串
        String randomKey = tokenUtil.getRandomKey();
        //生成token(通过用户名和签名时候用的随机数)
        String token = tokenUtil.generateToken(uuid, randomKey);
        result.put("randomkey",randomKey);
        result.put("token",token);
        return BaseResponseVo.success(result);
    }
}
