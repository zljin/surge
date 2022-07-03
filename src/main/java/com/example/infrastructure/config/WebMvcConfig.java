package com.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 拦截器一定要先这要配，防止注入其他工具类的时候报空指针异常
     * https://blog.csdn.net/jinyangbest/article/details/98205802
     *
     * @return
     */
    @Bean
    public RequestInterceptor userInterceptor() {
        return new RequestInterceptor();
    }


    /**
     * 添加Web项目的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过MyInterceptor类型的拦截器进行拦截
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/**/login/**").excludePathPatterns("/**/configuration/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }


}
