package com.ypjiao.mettingfilm.service;

import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;

public interface UserServiceImpl {
    String login(String username,String password) throws CommonServiceException;
}
