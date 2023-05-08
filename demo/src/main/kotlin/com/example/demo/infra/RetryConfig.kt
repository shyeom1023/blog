package com.example.demo.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.interceptor.RetryOperationsInterceptor
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate

@Configuration
@EnableRetry
class RetryConfig {

    @Bean
    fun retryTemplate(): RetryTemplate {
        val retryTemplate = RetryTemplate()
        val retryPolicy = SimpleRetryPolicy(3) // 재시도 정책
        val backOffPolicy = ExponentialBackOffPolicy() // 재시도 간격을 지수적으로 증가
        retryTemplate.setRetryPolicy(retryPolicy)
        retryTemplate.setBackOffPolicy(backOffPolicy)
        return retryTemplate
    }

    @Bean
    fun retryInterceptor(): RetryOperationsInterceptor {
        val interceptor = RetryOperationsInterceptor()
        interceptor.setRetryOperations(retryTemplate()) // retryTemplate()으로 RetryOperationsInterceptor 빈 생성
        return interceptor
    }
}