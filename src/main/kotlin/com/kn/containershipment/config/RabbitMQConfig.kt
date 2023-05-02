package com.kn.containershipment.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    private val SHIPMENT_QUEUE = "shipment"
    private val EXCHANGE = "exchange.direct"
    private val SHIPMENT_ROUTING_KEY = "routing.shipment"
    @Bean
    fun shipmentQueue(): Queue? {
        return Queue(SHIPMENT_QUEUE, true)
    }

    @Bean
    fun shipmentExchange(): DirectExchange? {
        return DirectExchange(EXCHANGE)
    }

    @Bean
    fun bindingToTestQueue(): Binding? {
        return BindingBuilder.bind(shipmentQueue())
                .to(shipmentExchange())
                .with(SHIPMENT_ROUTING_KEY)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter()
        return rabbitTemplate
    }
    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter();
    }
}