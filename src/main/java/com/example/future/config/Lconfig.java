package com.example.future.config;



import com.example.future.interceptor.Linterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器的配置文件
@Configuration
public class Lconfig implements WebMvcConfigurer {

    @Autowired
    private Linterceptor linterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(linterceptor).addPathPatterns("/**");//.excludePathPatterns("/login").excludePathPatterns("/register");
    }
}
