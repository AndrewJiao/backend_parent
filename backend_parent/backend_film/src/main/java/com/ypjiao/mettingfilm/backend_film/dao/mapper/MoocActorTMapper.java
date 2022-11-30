package com.ypjiao.mettingfilm.backend_film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeActorsRespVO;
import com.ypjiao.mettingfilm.backend_film.dao.entity.MoocActorT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author ypjiao
 * @since 2020-11-08
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {
    IPage<DescribeActorsRespVO> discribeActors(Page<DescribeActorsRespVO> page);
}
