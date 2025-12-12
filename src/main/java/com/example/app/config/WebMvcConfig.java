package com.example.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${app.upload.dir}")
	private String uploadDir;
	
	@Value("${app.upload.url}")
	private String uploadUrl;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(uploadUrl + "/**") // /uploads/**でアクセス可能
			.addResourceLocations("file:" + uploadDir + "/"); // 実ファイルを公開
	}
	

}
