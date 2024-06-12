package com.example.blog.mqtt.infra.config

import com.example.blog.mqtt.infra.properties.InfluxProperties
import com.influxdb.client.InfluxDBClient
import com.influxdb.client.InfluxDBClientFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class InfluxDBConfig(
        private val properties: InfluxProperties
) {

    @Bean
    fun influxDBClient(): InfluxDBClient {
        return InfluxDBClientFactory.create(
                properties.url,
                properties.token.toCharArray(),
                properties.org,
                properties.bucket)
    }
}