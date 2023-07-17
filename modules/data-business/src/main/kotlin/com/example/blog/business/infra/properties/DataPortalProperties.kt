package com.example.blog.business.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "data-portal")
data class DataPortalProperties(
    val endpoint: String
)
