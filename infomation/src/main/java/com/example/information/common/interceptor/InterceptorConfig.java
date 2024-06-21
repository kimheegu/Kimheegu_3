package com.example.information.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ViewNameInterceptor()) // 가로채기 할 요청 매핑이름
				.addPathPatterns("/*.do")
				.addPathPatterns("/*/*.do")
				.excludePathPatterns("/users/login"); // excludePath -> 확장
	}
}
