package com.ypjiao.mettingfilm.backend_common.dao.mapper;

import com.ypjiao.mettingfilm.backend_common.dao.entity.MoocUserT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ypjiao
 * @since 2021-05-13
 */
public interface MoocUserTMapper extends BaseMapper<MoocUserT> {
    List<MoocUserT> selectUserByName(@Param("username") String name);
}
