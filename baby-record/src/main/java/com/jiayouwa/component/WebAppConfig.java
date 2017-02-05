package com.jiayouwa.component;

import com.jiayouwa.web.interceptor.SessionInterceptor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhaoyan on 2017/2/5.
 */
@Service
@Data
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${webapp.wx.appId}")
    private String appId;
    @Value("${webapp.wx.appSecret}")
    private String appSecret;

    @Autowired
    private SessionInterceptor sessionInterceptor;



    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).excludePathPatterns("/wxLogin");
    }


}
