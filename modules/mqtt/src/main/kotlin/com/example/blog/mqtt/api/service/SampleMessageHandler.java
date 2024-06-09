package com.example.blog.mqtt.api.service;

import org.springframework.stereotype.Component;

@Component
class SampleMessageHandler {

    fun handle(message:SampleMessage) {
        logger.info("message arrived : $message")
    }
}

data class SampleMessage(val title:String, val content:String) // (2)