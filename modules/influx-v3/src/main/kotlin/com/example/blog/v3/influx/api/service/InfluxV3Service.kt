package com.example.blog.v3.influx.api.service

import com.influxdb.v3.client.InfluxDBClient
import com.influxdb.v3.client.Point
import com.influxdb.v3.client.query.QueryOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.stream.Collectors


@Service
class InfluxV3Service(private val influxDBClient: InfluxDBClient) {
    private val log: Logger = LoggerFactory.getLogger(InfluxV3Service::class.java)
    fun writePoint() {
        val point: Point = Point.measurement("temperature")
            .setTag("location", "west")
            .setField("value", 55.15)
            .setTimestamp(Instant.now().minusSeconds(-10))
        influxDBClient.writePoint(point)
    }

    fun writeLineProtocol() {
        val records = listOf(
            "temperature,location=north value=60.0",
            "temperature,location=north value=70.0",
            "temperature,location=north value=80.0"
        )

        records.forEach { record ->
            influxDBClient.writeRecord(record)
            println("Data has been written successfully: $record")
        }
    }

    fun selectSql() {
        try {
            val sql = "select time,location,value from temperature order by time desc limit 10"
            influxDBClient.query(sql).use { stream ->
                val resultList = stream.collect(Collectors.toList())
                resultList.forEach { row ->
                    log.info(
                        "| {}\t| {}\t| {}\t|",
                        String.format("%-8s", row.get(1)),
                        String.format("%-8s", row.get(2)),
                        String.format("%-30s", row.get(0))
                    )
                }
            }
        } catch (e: Exception) {
            log.error("Exception: {}", e.message, e)
        }
    }

    fun selectSqlWithPoint() {
        try {
            val sql = "select time,location,value from temperature order by time desc limit 10"
            influxDBClient.queryPoints(sql, QueryOptions.DEFAULTS).use { stream ->
                val resultList = stream.collect(Collectors.toList())
                resultList.forEach { p ->
                    val time = p.timestamp?.toLong() ?: 0L
                    log.info(
                        "| {}\t| {}\t| {}\t|",
                        String.format("%-8s", p.getTag("location")),
                        String.format("%-8s", p.getField("value")),
                        LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(time / 1_000_000_000, time % 1_000_000_000),
                            ZoneId.systemDefault()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            log.error("Exception: {}", e.message, e)
        }
    }


}
