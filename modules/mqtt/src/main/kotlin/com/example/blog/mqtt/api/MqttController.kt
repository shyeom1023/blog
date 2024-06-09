package com.example.blog.mqtt.api

import com.example.blog.mqtt.api.service.MqttPublisher
import org.eclipse.paho.client.mqttv3.MqttException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MqttController(
        private val mqttPublisher: MqttPublisher
) {

    @GetMapping("/publish")
    @Throws(MqttException::class)
    fun publish(@RequestParam message: String): String {
        mqttPublisher.sendMessage("test/topic", message)
        return "Message published: $message"
    }
}