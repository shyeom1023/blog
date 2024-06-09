package com.example.blog.mqtt.api.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SampleMessageHandler { // (1)

    private val log = LoggerFactory.getLogger(SampleMessageHandler::class.java)

    fun handle(message: SampleMessage) {
        log.info("message arrived : $message")
    }
}

data class SampleMessage(val title: String, val content: String) // (2)