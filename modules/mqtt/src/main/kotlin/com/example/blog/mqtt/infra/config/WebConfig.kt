package com.example.blog.mqtt.infra.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*") // 허용할 오리진 패턴 설정
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 설정
            .allowedHeaders("*") // 허용할 헤더 설정
            .allowCredentials(true) // 인증 정보를 포함할지 여부 설정
            .maxAge(3600) // preflight 요청의 최대 지속 시간 설정
    }
}

