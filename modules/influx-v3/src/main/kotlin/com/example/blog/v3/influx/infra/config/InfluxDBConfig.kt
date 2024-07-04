package com.example.blog.v3.influx.infra.config

import com.influxdb.v3.client.InfluxDBClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfluxDBConfig {

    @Bean
    fun influxDBClient(): InfluxDBClient {
        val host = "https://us-east-1-1.aws.cloud2.influxdata.com"
        val database = "test"
        val token = "qPGRzKndrmKCJYCRCSB34bMJwmy2koozJEdLU49yS5H8-nmgQNRZLRtTu0QbKDpLhDo7xfkczfVpKZdEBNDYCg==".toCharArray()

        return InfluxDBClient.getInstance(host, token, database)
    }
}