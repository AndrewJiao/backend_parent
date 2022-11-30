package com.ypjiao.mettingfilm.backend_film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeFilmsRespVo;
import com.ypjiao.mettingfilm.backend_film.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author ypjiao
 * @since 2020-11-08
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {
    IPage<DescribeFilmsRespVo> describeFilms(Page<DescribeFilmsRespVo> page);

    DescribeFilmRespVo describeFilmById(@Param("film_Id")String filmId);
}
