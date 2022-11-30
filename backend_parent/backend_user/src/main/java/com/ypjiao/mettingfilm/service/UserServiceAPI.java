package com.ypjiao.mettingfilm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import com.ypjiao.mettingfilm.backend_utils.utils.MD5Util;
import com.ypjiao.mettingfilm.dao.entity.MoocUserT;
import com.ypjiao.mettingfilm.dao.mapper.MoocUserTMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户模块业务实现
 */
@Service
public class UserServiceAPI implements UserServiceImpl {
    @Resource
    MoocUserTMapper mapper;

    /**
     * 用户数据验证实现
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) throws CommonServiceException {
        //根据用户名获取用户信息
        QueryWrapper<MoocUserT> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        List<MoocUserT> moocBackendUserTS = mapper.selectList(queryWrapper);
        MoocUserT moocBackendUserT;
        String data_password = null;
        if (moocBackendUserTS.size()<1){
            throw new CommonServiceException("404","用户名输入有误");
        }else {
            moocBackendUserT = moocBackendUserTS.get(0);
            data_password = moocBackendUserT.getUserPwd();
        }
        //验证密码是否正确
        if (!data_password.equals(MD5Util.encrypt(password))){
            throw new CommonServiceException("500","密码输入错误");
        }else{
            return moocBackendUserT.getUuid()+"";
        }
    }
}
