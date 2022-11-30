package com.mettingfilm.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author ：jiaojiao
 * @date ：Created in 2021/5/12 14:17
 * @description：自定义Zuul的filter
 * @modified By：
 */
//记得，还要将过滤器注册bean
public class MyZuulFilter extends ZuulFilter {

    /**
     * filter类型，如post，pre
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 数字越小优先级越大
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter业务方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //可以从currentContext中获取到Requets对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("===============");
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println("filter过滤"+s);
        }
        return null;
    }
}
