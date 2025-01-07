package com.example.javaproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://jjol.netlify.app") // 허용된 Origin 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용된 HTTP 메서드
                .allowedHeaders("*") // 허용된 헤더
                .exposedHeaders("Authorization", "RefreshToken") // 응답 헤더로 노출할 헤더
                .allowCredentials(true); // 쿠키 포함된 인증 정보 허용
    }
}
