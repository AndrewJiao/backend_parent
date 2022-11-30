package com.ypjiao.testng.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Collections2;
import com.google.gson.JsonObject;
import com.ypjiao.mettingfilm.apis.film.vo.DescribeFilmRespVo;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.testng.config.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/13 0:06
 * @description：测试各个服务
 * @modified By：
 */
@Slf4j
public class TestMooc {
    @Test
    public void testFilm(){
        String url = "http://localhost:7203/films/2";
        RestTemplate restTemplate = Template.getRestTemplate();
        BaseResponseVo forObject = restTemplate.getForObject(url, BaseResponseVo.class);
        log.info("resoult={}",forObject);
    }
    @Test(dataProvider = "filmName")
    //测试film接口
    public void testFilms(String filmNameCheck){
        String url = "http://localhost:8080/film-api/films/getFilms";
        RestTemplate restTemplate = Template.getRestTemplate();

        String jsonResult = restTemplate.getForObject(url, String.class);

        log.info("jsonResult={}",jsonResult);
        //封装json数据
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        //提取json数据中的data
        JSONObject data = jsonObject.getJSONObject("data");
        //以集合方式提取json中的film数组
        JSONArray films = data.getJSONArray("films");
        //转化json对象为实体
        List<DescribeFilmRespVo> describeFilmRespVos = films.toJavaList(DescribeFilmRespVo.class);
        //校验todo
        assert(describeFilmRespVos.size()!=0);
        describeFilmRespVos.forEach(e->{
            String filmName = e.getFilmName();
            log.info("filmName={}，checkName={}",filmName,filmNameCheck);
                }
        );
    }
    @DataProvider(name = "filmName")
    public Object[] getNames(){
        return new String[]{"我不是药神","helloWorld"};
    }
}
