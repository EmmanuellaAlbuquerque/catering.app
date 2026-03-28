package com.catering.app.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final OnboardingRedirectInterceptor onboardingRedirectInterceptor;

    public WebMvcConfig(OnboardingRedirectInterceptor onboardingRedirectInterceptor) {
        this.onboardingRedirectInterceptor = onboardingRedirectInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(onboardingRedirectInterceptor);
    }
}
