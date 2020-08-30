package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  Druid配置类
 */
@Configuration
public class DruidConfig {
    // 配置参数属性前缀，让yml中Druid的参数配置起效
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid监控
    // 1. 配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*"
        );
        Map<String, Object> initParameter = new HashMap<>();
        initParameter.put("loginUsername", "admin");
        initParameter.put("loginPassword", "123456");
        initParameter.put("allow", "");         // 默认允许所有人访问
        initParameter.put("deny", "192.168.3.6");  // 指定黑名单
        bean.setInitParameters(initParameter);
        return bean;
    }

    // 2. 配置一个Web监控的Filter
    @Bean
    public FilterRegistrationBean webStateFileter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, Object> initParameter = new HashMap<>();
        initParameter.put("exclusions", "*.js, *.gif, *.jpg, *.css, /druid/*");
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setInitParameters(initParameter);
        return bean;
    }
}
