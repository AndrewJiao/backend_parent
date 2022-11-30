package com.ypjiao.mettingfilm.backend_common.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.backend_common.BackendCommonApplicationTests;
import com.ypjiao.mettingfilm.backend_common.dao.mapper.MoocUserTMapper;
import com.ypjiao.mettingfilm.backend_common.dao.entity.MoocUserT;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class UserTest extends BackendCommonApplicationTests {
    @Resource
    MoocUserTMapper mapper;
    @Test
    public void add(){
        //新增数据
//        MoocBackendUserT moocBackendUserT = new MoocBackendUserT();
//        moocBackendUserT.setUserName("admin");
//        moocBackendUserT.setUserPwd("admin");
//        moocBackendUserT.setUserPhone("18789729822");
//        mapper.insert(moocBackendUserT);
        //新增多条数据
        for (int i = 0; i < 5; i++) {
            MoocUserT moocBackendUserTs = new MoocUserT();
            moocBackendUserTs.setUserName("admin"+i);
            moocBackendUserTs.setUserPwd("admin"+i);
            moocBackendUserTs.setUserPhone("18789729822");
            mapper.insert(moocBackendUserTs);
        }

    }
    @Test
    public void select(){
        //根据主键查询单条数据
//        MoocBackendUserT moocBackendUserT = mapper.selectById(2);
//        System.out.println("moocBackendUserT="+moocBackendUserT);
        //查询多条数据
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("user_name",new String[]{"admin3","admin4"});
        //自动添加and
        wrapper.eq("user_pwd","admin4");
        List<MoocUserT> moocBackendUserTS = mapper.selectList(wrapper);
        moocBackendUserTS.stream().forEach(
                System.out::println
        );
    }

    /**
     * 分页查询
     */
    @Test
    public void selectByPage(){
        //分页参数，每一页两行展示第一页
        Page<MoocUserT> page = new Page<MoocUserT>(1,2);
        //设置顺序，需分别设置顺序以及依据的字段
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(true);
        orderItem.setColumn("user_name");
        page.setOrders(Collections.singletonList(orderItem));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("user_name",new String[]{"admin3","admin4","admin2","admin1"});
        //查询结果是一个ipage对象
        IPage iPage = mapper.selectPage(page, wrapper);
        iPage.getRecords().stream().forEach(
                System.out::println
        );

    }

    /**
     * 自定义sql
     */
    @Test
    public void selectByUsername(){
        List<MoocUserT> ypjiao = mapper.selectUserByName("jiangzh");
        ypjiao.stream().forEach(
                System.out::println
        );

    }

    @Test
    public void update(){
        //根据主键更新
//        MoocBackendUserT moocBackendUserT = new MoocBackendUserT();
//        moocBackendUserT.setUuid(2);
//        moocBackendUserT.setUserName("ypjiao");
//        moocBackendUserT.setUserPwd("123423");
//        moocBackendUserT.setUserPhone("18926445771");
//        int i = mapper.updateById(moocBackendUserT);
//        System.out.println("i="+i);

        //根据条件更新数据
        MoocUserT moocBackendUserT = new MoocUserT();
        moocBackendUserT.setUuid(2);
        moocBackendUserT.setUserName("ypjiao");
        moocBackendUserT.setUserPwd("11111");
        moocBackendUserT.setUserPhone("18926445771");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("user_name",new String[]{"admin1","admin2"});
        mapper.update(moocBackendUserT,wrapper);

    }
    @Test
    public void del(){
        //根据主键删除
        int i = mapper.deleteById(2);

    }
}
