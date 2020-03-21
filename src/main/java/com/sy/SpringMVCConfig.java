package com.sy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.sy.core.inteceptor.WebInterceptor;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport  {

	@Autowired
	private WebInterceptor webInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(webInterceptor).addPathPatterns("/**").excludePathPatterns("/#/**");
	}
	
}
