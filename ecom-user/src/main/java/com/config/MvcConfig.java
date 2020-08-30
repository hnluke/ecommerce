package com.config;


import com.interceptor.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MvcConfig implements WebMvcConfigurer {

    //视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认访问login.html

        registry.addViewController("/login").setViewName("userpages/login");
        registry.addViewController("/index").setViewName("userpages/index");
        registry.addViewController("/user").setViewName("userpages/login");
        registry.addViewController("/comm").setViewName("commpages/login");
        registry.addViewController("/deliver").setViewName("deliverpages/login");
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //排除"/login.html", "/", "/user/login","/error" 请求不拦截
        //排除静态资源不拦截"/css/**", "/images/**","/js/**","/webjars/**" 请求不拦截
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user", "/", "/comm", "/deliver" , "/user/login",
                        "/user/deliverlogin", "/user/commlogin", "/data/**", "/css/**",
                        "/images/**","/js/**", "/dist/**", "/less/**", "/vendor/**",
                        "/webjars/**","/error");
    }
}
