package com.example.blog.mqtt.api

import com.example.blog.mqtt.api.service.MqttPublisher
import org.eclipse.paho.client.mqttv3.MqttException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.web.bind.annotation.*

@RestController
class MqttController(
    private val mqttPublisher: MqttPublisher,
    private val mqttChannelAdapter: MqttPahoMessageDrivenChannelAdapter
) {

    @GetMapping("/publish")
    @Throws(MqttException::class)
    fun publish(@RequestParam message: String): String {
        mqttPublisher.sendMessage("test/topic", message)
        return "Message published: $message"
    }

    @PostMapping("/publish")
    @Throws(MqttException::class)
    fun publish2(@RequestBody request: PublishDto): String {
        mqttPublisher.sendMessage(request.topic, request.message)
        return "Message published: ${request.message}"
    }

    @PostMapping("/subscribe")
    fun addTopic(@RequestParam topic: String): ResponseEntity<String> {
        try {
            mqttChannelAdapter.addTopic(topic)
            return ResponseEntity.ok("Subscribed to topic: $topic")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to subscribe: ${e.message}")
        }
    }

    @PostMapping("/unsubscribe")
    fun removeTopic(@RequestParam topic: String): ResponseEntity<String> {
        try {
            mqttChannelAdapter.removeTopic(topic)
            return ResponseEntity.ok("Unsubscribed from topic: $topic")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unsubscribe: ${e.message}")
        }
    }

    @GetMapping("/subscribed")
    fun getSubscribedTopics(): ResponseEntity<Array<String>> {
        return ResponseEntity.ok(mqttChannelAdapter.topic)
    }
}

data class PublishDto(
    val topic: String,
    val message: String,
)
