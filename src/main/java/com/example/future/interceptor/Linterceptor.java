package com.example.future.interceptor;

import com.alibaba.fastjson.JSONObject;

import com.example.future.pojo.Result;
import com.example.future.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class Linterceptor implements HandlerInterceptor {

    //ctr + o
    @Override//运行前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域支持
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String url = request.getRequestURI().toString();
        log.info(url);

        //放行
        if(url.contains("/login")||url.contains("/register")){

            return true;
        }

        //令牌校验
        //获取
        String jwt = request.getHeader("token");

        //校验
        //这个方法 --- <> null and .length() <> 0
        if(! StringUtils.hasLength(jwt)){
            log.info("未检测到令牌");
            Result error = Result.error("no jwt");

            //类型强转 --
            String notLogin = JSONObject.toJSONString(error);

            //返回
            response.getWriter().write(notLogin);
            return false;
        }
        if(url.contains("/user") && !url.contains("/manager")){
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
                response.getWriter().write(notLogin);
                return false;
            }
        }
            else if(url.contains("/manager")){
                try {
                    JwtUtils.parseJWT(jwt,2);
                }catch(Exception e){
                    e.printStackTrace();
                    log.info("非法令牌");
                    Result error = Result.error("illegal jwt");

                    //类型强转 --
                    String notLogin = JSONObject.toJSONString(error);

                    //返回
                    response.getWriter().write(notLogin);
                    return false;
                }
            }


    return true;
    }

    @Override//运行后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
