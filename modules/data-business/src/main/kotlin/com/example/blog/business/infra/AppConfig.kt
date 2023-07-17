package com.example.blog.business.infra

import com.example.blog.business.component.data.BusinessClient
import com.example.blog.business.component.data.BusinessUriHelper
import com.example.blog.business.infra.properties.DataPortalProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    DataPortalProperties::class
)
class AppConfig(
    private val webConfig: WebConfig
) {
    @Bean
    fun businessClient(properties: DataPortalProperties): BusinessClient {
        return BusinessClient(
            webConfig.restTemplate(),
            BusinessUriHelper(properties.endpoint)
        )
    }
}