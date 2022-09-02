package com.spring.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*
    @Autowired
    private BootInterceptor bootInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new BootInterceptor())
        registry.addInterceptor(bootInterceptor)
                .addPathPatterns("/**") // 적용할 URL (모든 URL )
                .excludePathPatterns("/","/member/insert", "/js/**", "/css/**", "/img/**", "/docs/**", "/login/**"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
    */


}
