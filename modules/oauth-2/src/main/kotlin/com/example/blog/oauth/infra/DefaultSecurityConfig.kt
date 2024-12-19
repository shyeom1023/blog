package com.example.blog.oauth.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class DefaultSecurityConfig(
    private val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .formLogin(Customizer.withDefaults()) // 로그인 폼 활성화

        return http.build()
    }


    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val userDetails: UserDetails = User.withUsername("custom-user")
            .password(passwordEncoder.encode("custom-password"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(userDetails)
    }
}
