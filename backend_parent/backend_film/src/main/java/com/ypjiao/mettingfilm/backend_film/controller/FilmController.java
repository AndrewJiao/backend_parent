package com.ypjiao.mettingfilm.backend_film.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeActorsRespVO;
import com.ypjiao.mettingfilm.backend_film.controller.vo.DescribeFilmsRespVo;
import com.ypjiao.mettingfilm.backend_film.controller.vo.FilmSavedVo;
import com.ypjiao.mettingfilm.backend_film.service.FilmServiceAPI;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BasePageReqVo;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/films/")
@RestController
public class FilmController {
    @Autowired
    FilmServiceAPI serviceAPI;
    @RequestMapping(value = "actors", method = RequestMethod.GET)
    /**
     * 演员列表查询
     * 1.自定义页面VO对象封装来自页面的nowPage和pageSize参数
     * 2.新建Page分页对象并传入上一步两个参数
     * 3。在service中调用通用mapper传入指定Page分页对象查询指定结果
     * 4. 自定义DescribeActorsRespVO.java用于接收分页结果
     * 5. 将返回的分页数据转化为map集合
     */
    public BaseResponseVo getActors(BasePageReqVo pageReqVo) throws CommonServiceException {
        pageReqVo.checkVoParam();
        IPage<DescribeActorsRespVO> page = serviceAPI.getActors(pageReqVo.getNowPage(),pageReqVo.getPageSize());
        HashMap<String, Object> map = discriptActorsResponse(page, "actors");

        return BaseResponseVo.success(map);
    }

    /**
     * 浏览影片列表接口
     * 流程同上一步用户查询，从此步骤可看出vo要根据需求文档中的需求定义
     * 1.自定义sql配置关联查询mooc_film_info_t和 mooc_film_t
     * 2.接口添加对应sql配置的方法并自定义DescribeFilmsRespVo对象接收返回数据
     * 3.返回page同用户查询一样封装成map直接返回
     */
    @RequestMapping(value = "getFilms", method = RequestMethod.GET)
    public BaseResponseVo getFilms(BasePageReqVo pageReqVo) throws CommonServiceException {
        pageReqVo.checkVoParam();
        IPage<DescribeFilmsRespVo> page = serviceAPI.getFilms(pageReqVo.getNowPage(),pageReqVo.getPageSize());
        HashMap<String, Object> map = discriptActorsResponse(page, "films");

        return BaseResponseVo.success(map);
    }

    /**
     * 根据电影编号查询电影信息接口
     * 流程一致，由于涉及复杂查询需要重新定义mapper文件和mapper接口，接口用BaseResponseVo对象返回
     * 1.通过@PathVariable接收查询的电影编号
     * 2.定义mapper文件中的sql，
     *   该查询较为复杂，涉及表mooc_film_t：电影详细信息，mooc_film_info_t：电影大致信息
     *   1. 用户需要从mooc_film_t表中，提取处film_cat字段的值“#1#2#3#”，通过tram()和replace()转化为1,2,3格式
     *   2. 利用转化后的数组“1，2，3”，通过横传纵函数FIND_IN_SET(“1，2，3”)查询mooc_cat_dict_t表，得到"分组数据"
     *   3. 利用纵转横函数FIND_IN_SET()将"分组数据"转为行内的字段数据,字段别名设为filmCats
     *   4. 以上面3步方法达到将#1#2#3#查询变为”爱情，戏剧，动画“的效果
     * 3.返回单个电影数据，根据返回结果是否为空走不同的Vo处理逻辑
     */
    @RequestMapping(value = "{filmId}", method = RequestMethod.GET)
    public BaseResponseVo getFilm(@PathVariable String filmId) throws CommonServiceException {
        DescribeFilmRespVo film = serviceAPI.getFilm(filmId);
        if (film != null){
            return BaseResponseVo.success(film);
        }else {
            return BaseResponseVo.success();
        }
    }

    /**
     * 添加电影数据
     * 1. 定义需要的FilmSavedVo
     * 2. 将FilmSavedVo中的数据一个个挖出，利用MybatisPlus的通用Mapper
     * 存储数据到mooc_film_t，mooc_film_info_t，mooc_film_actor_t表中
     * 3. 添加事务
     */
    @RequestMapping(value = "film:add", method = RequestMethod.POST)
    public BaseResponseVo filmAdd(@RequestBody FilmSavedVo filmSavedVo) throws CommonServiceException {
        filmSavedVo.checkVoParam();
        serviceAPI.filmAdd(filmSavedVo);
        return BaseResponseVo.success();
    }


    private HashMap<String, Object> discriptActorsResponse(IPage page, String title) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put(title,page.getRecords());
        map.put("totalSize",page.getPages());
        map.put("totalPages",page.getTotal());
        map.put("pageSize",page.getSize());
        map.put("nowPage",page.getCurrent());
        return map;
    }
}
