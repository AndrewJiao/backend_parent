package com.ypjiao.mettingfilm.backend_film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeActorsRespVO;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeFilmsRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.FilmSavedVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;

public interface FilmServiceAPI {
    IPage<DescribeActorsRespVO> getActors(int nowPage,int pageSize) throws CommonServiceException;

    IPage<DescribeFilmsRespVo> getFilms(int nowPage, int pageSize) throws CommonServiceException;

    DescribeFilmRespVo getFilm(String filmId) throws CommonServiceException;

    void filmAdd(FilmSavedVo film) throws CommonServiceException;
}

