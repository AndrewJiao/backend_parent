package com.ypjiao.mettingfilm.apis.film;

import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/12 2:41
 * @description：feign影片模块公共接口api
 * @modified By：
 */
public interface FilmFeignApi {
    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.GET)
    BaseResponseVo<DescribeFilmRespVo> getFilm(@PathVariable String filmId) throws CommonServiceException;
}
