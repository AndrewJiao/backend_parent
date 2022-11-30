package com.ypjiao.mettingfilm.backend_hall.feignapi;

import com.ypjiao.mettingfilm.apis.film.FilmFeignApi;
import org.springframework.cloud.openfeign.FeignClient;
//由于只有一个服务端film，暂时不做ribbon和降级设置
@FeignClient(name = "film-service")
public interface HallToFilmFeignApi extends FilmFeignApi {
}
