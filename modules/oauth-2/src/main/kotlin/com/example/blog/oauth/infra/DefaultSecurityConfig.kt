//package com.example.blog.oauth.infra
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.Customizer
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//class DefaultSecurityConfig {
//    @Bean
//    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .authorizeHttpRequests { it.anyRequest().authenticated() }
//            .formLogin(Customizer.withDefaults()) // 로그인 폼 활성화
//
//        return http.build()
//    }
//}
