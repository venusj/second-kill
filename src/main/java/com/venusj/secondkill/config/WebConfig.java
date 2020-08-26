package com.venusj.secondkill.config;

import com.venusj.secondkill.resolver.UserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author zhangjh
 * @date 2020/8/26
 * @desc
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userHandlerMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}
