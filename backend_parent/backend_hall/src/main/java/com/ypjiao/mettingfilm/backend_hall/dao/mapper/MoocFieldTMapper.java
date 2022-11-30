package com.ypjiao.mettingfilm.backend_hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallReqVo;
import com.ypjiao.mettingfilm.backend_hall.controller.vo.HallRespVo;
import com.ypjiao.mettingfilm.backend_hall.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author ypjiao
 * @since 2020-11-12
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {
    IPage<HallRespVo> describeShow(Page<HallReqVo> page, @Param("ew") QueryWrapper wrapper);
}
