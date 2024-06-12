package com.example.blog.mqtt.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "influxdb")
data class InfluxProperties(
        var url: String = "",
        var token: String = "",
        var org: String = "",
        var bucket: String = "",
)