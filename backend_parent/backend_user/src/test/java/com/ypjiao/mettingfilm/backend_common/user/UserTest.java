package com.ypjiao.mettingfilm.backend_common.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypjiao.mettingfilm.BackendUserApplicationTests;
import com.ypjiao.mettingfilm.backend_utils.utils.MD5Util;
import com.ypjiao.mettingfilm.dao.entity.MoocUserT;
import com.ypjiao.mettingfilm.dao.mapper.MoocUserTMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendUserApplicationTests {

    @Resource
    MoocUserTMapper moocBackendUserTMapper;
    @Test
    public void testUserSelect(){
        QueryWrapper<MoocUserT> moocBackendUserTQueryWrapper = new QueryWrapper<>();
        moocBackendUserTQueryWrapper.in("user_name", new String[]{"ypjiao", "admin1"});
        List<MoocUserT> moocBackendUserTS = moocBackendUserTMapper.selectList(moocBackendUserTQueryWrapper);
        moocBackendUserTS.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void testUserInsertMD5(){
        MoocUserT moocBackendUserT = new MoocUserT();
        moocBackendUserT.setUserName("Andrew_Jiao");
        moocBackendUserT.setUserPhone("18789456214");
        moocBackendUserT.setUserPwd(MD5Util.encrypt("553211826"));
        int insert = moocBackendUserTMapper.insert(moocBackendUserT);
    }

}
