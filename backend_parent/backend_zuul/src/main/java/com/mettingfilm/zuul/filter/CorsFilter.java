package com.mettingfilm.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : jiangzh
 * @program : com.mooc.jiangzh.springcloud.filters.pre
 * @description : 解决跨域问题
 **/
public class CorsFilter extends ZuulFilter {

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 跨域
        HttpServletResponse response = ctx.getResponse();
        //允许的来源 *-》所有
        response.addHeader("Access-Control-Allow-Origin", "*");
        //允许来访问的可用信息
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
        //允许的head头
        response.setHeader("Access-Control-Allow-Headers", "DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range,Authorization");
        //设置返回格式
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /*
         * 跨域资源共享
         *   - 这是http协议规定的安全策略
         *   - 配置资源共享方式和目标方
         *   前端： node+vue -》admin.meetingfilm.com
         *   后端： springboot -》backend.meetingfilm.com
         *   示例：
         *       缺陷：如果出现跨域策略不足的情况下，需要修改代码，重新部署
         * */
        return null;
    }

}