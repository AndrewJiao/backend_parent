package com.mettingfilm.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ypjiao.mettingfilm.backend_utils.common.vo.BaseResponseVo;
import com.ypjiao.mettingfilm.backend_utils.properties.JwtProperties;
import com.ypjiao.mettingfilm.backend_utils.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author : jiangzh
 * @program : com.mooc.jiangzh.springcloud.filters.pre
 * @description : JWT信息验证Filter
 **/
public class JWTFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // JWT工具类
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        JwtProperties jwtProperties = JwtProperties.getJwtProperties();


        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取当前请求和返回值
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        // 提前设置请求继续，如果失败则修改此内容
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        System.out.println("JWTfILTER");
        if (request.getServletPath().endsWith("/" + jwtProperties.getAuthPath())) {
            return null;
        }

        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    renderJson(ctx, response,
                            BaseResponseVo.noLogin());
                }else{
                    //解析处JWP中的payload -> userid - randomkey
                    String uuid = jwtTokenUtil.getUsernameFromToken(authToken);
                    String randomKey = jwtTokenUtil.getMd5KeyFromToken(authToken);
                    System.out.println("username="+uuid+";ramdomkey="+randomKey);
                    //是否需要验签，验签算法
                    //判断userid是否有效
                    // TODO: 2021/5/12
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                renderJson(ctx, response,
                        BaseResponseVo.noLogin());
            }
        } else {
            //header没有带Bearer字段
            renderJson(ctx, response,
                    BaseResponseVo.noLogin());
        }

        return null;
    }

    /**
     * 渲染json对象
     */
    public static void renderJson(RequestContext ctx, HttpServletResponse response, Object jsonObject) {
        // 设置终止请求
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(JSONObject.toJSONString(jsonObject));
    }

}