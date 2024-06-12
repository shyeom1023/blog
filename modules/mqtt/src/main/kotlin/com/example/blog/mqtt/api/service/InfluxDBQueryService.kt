package com.example.blog.mqtt.api.service

import com.example.blog.mqtt.api.InfluxDBQueryRequest
import com.example.blog.mqtt.infra.properties.InfluxProperties
import com.influxdb.client.InfluxDBClient
import com.influxdb.client.QueryApi
import com.influxdb.query.FluxTable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class InfluxDBQueryService(
        private val influxDBClient: InfluxDBClient,
        private val properties: InfluxProperties
) {

    private val logger: Logger = LoggerFactory.getLogger(InfluxDBQueryService::class.java)

    fun queryData(query: String): List<Map<String, Any>> {
        logger.info("query: {}", query)
        val queryApi: QueryApi = influxDBClient.queryApi
        val tables: List<FluxTable> = queryApi.query(query, properties.org)
        val results = mutableListOf<Map<String, Any>>()

        for (table in tables) {
            for (record in table.records) {
                val data = mutableMapOf<String, Any>()
                data["time"] = record.getValueByKey("_time") as Any
                data["value"] = record.getValueByKey("_value") as Any
                data["measurement"] = record.measurement as Any
                data.putAll(record.values)
                results.add(data)
            }
        }

        return results
    }

    fun buildFluxQuery(request: InfluxDBQueryRequest): String {
        val stopClause = request.stop?.let { "|> range(start: ${request.start}, stop: ${request.stop})" }
                ?: "|> range(start: ${request.start})"

        val tagClauses = request.tags?.entries?.joinToString(" and ") { (key, value) -> "r[\"$key\"] == \"$value\"" }
        val tagClause = tagClauses?.let { "|> filter(fn: (r) => $it)" } ?: ""

        val fieldClauses = request.fields?.joinToString(" or ") { field -> "r._field == \"$field\"" }
        val fieldClause = fieldClauses?.let { "|> filter(fn: (r) => $it)" } ?: ""

        val valueClauses = request.values?.joinToString(" or ") { value ->
            when (value) {
                is Number -> "r._value == $value"
                is String -> "r._value == \"$value\""
                else -> throw IllegalArgumentException("Unsupported value type")
            }
        }
        val valueClause = valueClauses?.let { "|> filter(fn: (r) => $it)" } ?: ""

        return """
            from(bucket: "${properties.bucket}")
              $stopClause
              |> filter(fn: (r) => r._measurement == "${request.measurement}")
              $tagClause
              $fieldClause
              $valueClause
        """.trimIndent()
    }
}
