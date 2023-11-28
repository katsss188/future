package com.example.future.filter;

import com.alibaba.fastjson.JSONObject;

import com.example.future.pojo.Result;
import com.example.future.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.logging.Logger;

//注：未启动过滤器
//@WebFilter(urlPatterns = "/*")
public class Lfilter implements Filter {
    private static Logger log = Logger.getLogger("testLog");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转类型
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取url
        String url = httpServletRequest.getRequestURI().toString();
        log.info(url);

        //放行
        if(url.contains("/login")||url.contains("/register")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //令牌校验
        //获取
        String jwt = httpServletRequest.getHeader("token");

        //校验
        //这个方法 --- <> null and .length() <> 0
        if(! StringUtils.hasLength(jwt)){
            log.info("未检测到令牌");
            Result error = Result.error("no jwt");

            //类型强转 --
            String notLogin = JSONObject.toJSONString(error);

            //返回
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        //解析JWT
        try {
            JwtUtils.parseJWT(jwt,1);
        }catch(Exception e){
            e.printStackTrace();
            log.info("非法令牌");
            Result error = Result.error("illegal jwt");

            //类型强转 --
            String notLogin = JSONObject.toJSONString(error);

            //返回
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        //解析函数未出错，放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}