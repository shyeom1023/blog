package com.example.blog.v3.influx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InfluxV3App

fun main(args: Array<String>) {
    runApplication<InfluxV3App>(*args)
}
