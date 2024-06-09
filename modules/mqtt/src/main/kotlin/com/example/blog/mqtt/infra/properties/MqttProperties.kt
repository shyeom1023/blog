package com.example.blog.mqtt.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration // (1)

@ConfigurationProperties(prefix = "mqtt")
data class MqttProperties(
        var url: String = "",
        var port: Int  = 0,
        var qos: Int = 0,
        var topic: String = "",
) {
    fun connectionInfo() = "$url:$port"
}