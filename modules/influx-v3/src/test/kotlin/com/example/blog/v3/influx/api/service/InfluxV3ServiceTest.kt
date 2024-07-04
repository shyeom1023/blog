package com.example.blog.v3.influx.api.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InfluxV3ServiceTest {

    @Autowired
    private lateinit var influxV3Service: InfluxV3Service


    @Test
    fun `create test`() {
        influxV3Service.writeLineProtocol()
//        influxV3Service.writePoint()
    }

    @Test
    fun `select test`() {
//        influxV3Service.selectSql()
        influxV3Service.selectSqlWithPoint()
    }
}