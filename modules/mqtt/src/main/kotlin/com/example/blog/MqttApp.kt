package com.example.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqttApp

fun main(args: Array<String>) {
    runApplication<MqttApp>(*args)
}
