package com.example.blog.mqtt.api

import com.example.blog.mqtt.api.service.InfluxDBQueryService
import com.example.blog.mqtt.api.service.InfluxDBService
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/influxdb")
class InfluxDBController(
        private val influxDBService: InfluxDBService,
        private val influxDBQueryService: InfluxDBQueryService
) {

    @PostMapping("/query")
    fun query(@RequestBody request: InfluxDBQueryRequest): List<Map<String, Any>> {
        val query = influxDBQueryService.buildFluxQuery(request)
        return influxDBQueryService.queryData(query)
    }

    @PostMapping("/write")
    fun writeData(@RequestBody requestBody: InfluxWriteRequest) {
        val measurement = requestBody.measurement
        val tags = requestBody.tags
        val fields = requestBody.fields
        val timestamp = Instant.now()
        influxDBService.writeData(measurement, tags, fields, timestamp)
    }
}

data class InfluxWriteRequest(
        val measurement: String,
        val tags: Map<String, String>,
        val fields: Map<String, Any>
)

data class InfluxDBQueryRequest(
        val measurement: String,
        val start: String,
        val stop: String? = null,
        val tags: Map<String, String>? = null,
        val fields: List<String>? = null,
        val values: List<Any>? = null
)