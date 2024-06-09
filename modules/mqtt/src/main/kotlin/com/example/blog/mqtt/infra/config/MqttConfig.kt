package com.example.blog.mqtt.infra.config

import com.example.blog.mqtt.api.service.SampleMessage
import com.example.blog.mqtt.api.service.SampleMessageHandler
import com.example.blog.mqtt.infra.properties.MqttProperties
import com.fasterxml.jackson.databind.ObjectMapper
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.dsl.Transformers
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.handler.annotation.Header

@Configuration
@EnableConfigurationProperties
class MqttConfig(
        private val sampleMessageHandler: SampleMessageHandler,
        private val mqttProperties: MqttProperties,
        private val objectMapper: ObjectMapper,
) {

    @Bean
    fun mqttPahoClientFactory(): MqttPahoClientFactory { // (1)
        return DefaultMqttPahoClientFactory()
                .apply {
                    connectionOptions = connectOptions()
                }
    }

    private fun connectOptions(): MqttConnectOptions {
        return MqttConnectOptions()
                .apply { // (2)
                    serverURIs = arrayOf(mqttProperties.connectionInfo())
                }
    }

    @Bean
    fun mqttInboundFlow() = integrationFlow(mqttChannelAdapter()) { // (3)
        transform(Transformers.fromJson(SampleMessage::class.java)) // (4)
        handle {
            sampleMessageHandler.handle(it.payload as SampleMessage) // (5)
        }
    }

    private fun mqttChannelAdapter(): MqttPahoMessageDrivenChannelAdapter { // (6)
        return MqttPahoMessageDrivenChannelAdapter(
                MqttClient.generateClientId(),
                mqttPahoClientFactory(),
                mqttProperties.topic)
                .apply {
                    setCompletionTimeout(5000)
                    setConverter(DefaultPahoMessageConverter())
                    setQos(mqttProperties.qos)
                }
    }

    @Bean
    fun mqttOutboundFlow() = integrationFlow(MQTT_OUTBOUND_CHANNEL) { // (7)
        transform<Any> { // (8)
            when (it) {
                is SampleMessage -> objectMapper.writeValueAsString(it)
                else -> it
            }
        }
        handle(mqttOutboundMessageHandler()) // (9)
    }

    private fun mqttOutboundMessageHandler(): MessageHandler { // (10)
        return MqttPahoMessageHandler(MqttAsyncClient.generateClientId(), mqttPahoClientFactory())
                .apply {
                    setAsync(true)
                    setDefaultTopic(mqttProperties.topic)
                    setDefaultQos(mqttProperties.qos)
                }
    }

    @MessagingGateway(defaultRequestChannel = MQTT_OUTBOUND_CHANNEL)
    interface MqttOutboundGateway { // (11)

        @Gateway
        fun publish(@Header(MqttHeaders.TOPIC) topic: String, data: String) // (12)

        @Gateway
        fun publish(data: SampleMessage) // (13)
    }

    companion object {
        const val MQTT_OUTBOUND_CHANNEL = "outboundChannel"
    }

}