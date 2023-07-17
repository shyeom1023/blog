package com.example.blog.business.infra

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplateCustom = restTemplateCustom()
//        restTemplateCustom.requestFactory = httpComponentsClientHttpRequestFactoryTimeOut()

        return restTemplateCustom

    }

    fun restTemplateCustom(): RestTemplate {
        return RestTemplateBuilder()
            .build()
    }

//    fun httpComponentsClientHttpRequestFactoryTimeOut(): HttpComponentsClientHttpRequestFactory {
//        val httpRequestFactory = HttpComponentsClientHttpRequestFactory()
//        httpRequestFactory.setReadTimeout(5000)
//        httpRequestFactory.setConnectTimeout(3000)
//        return httpRequestFactory
//    }

//    fun closableHttpClient(): ClosableHttpClient {
//        closeableHttpClientBeanFactory
//    }

    fun closableHttpClientBeanFactory(){

    }
}