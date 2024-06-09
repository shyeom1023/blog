package com.example.blog.mqtt.api.service

import com.example.blog.mqtt.infra.config.MqttConfig
import org.eclipse.paho.client.mqttv3.MqttException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MqttPublisher(
        private val mqttOutboundGateway: MqttConfig.MqttOutboundGateway
) {

    private val logger: Logger = LoggerFactory.getLogger(MqttPublisher::class.java)

    @Throws(MqttException::class)
    fun sendMessage(topic: String, payload: String) {
        mqttOutboundGateway.publish(topic, payload)
        logger.info("Message sent to topic '{}': {}", topic, payload)
        mqttOutboundGateway.publish(SampleMessage("test", payload))
        logger.info("Message sent to topic '{}': {}", "sample", payload)
    }
}