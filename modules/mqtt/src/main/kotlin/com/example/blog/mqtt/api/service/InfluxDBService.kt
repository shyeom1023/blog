package com.example.blog.mqtt.api.service

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.write.Point
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class InfluxDBService(private val influxDBClient: InfluxDBClient) {

    fun writeData(measurement: String, tags: Map<String, String>, fields: Map<String, Any>, timestamp: Instant) {
        val point = Point(measurement)
                .addTags(tags)
                .addFields(fields)
                .time(timestamp, WritePrecision.MS)

        influxDBClient.writeApiBlocking.writePoint(point)
    }
}