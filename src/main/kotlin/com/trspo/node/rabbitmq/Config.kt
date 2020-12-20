package com.trspo.node.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Value("\${rabbitmq.queue}")
    lateinit var queue: String

    @Value("\${rabbitmq.exchange}")
    lateinit var exchange: String

    @Bean
    fun queue(): Queue {
        return Queue(queue,false,false,true)
    }

    @Bean
    fun exchange(): FanoutExchange {
        return FanoutExchange(exchange)
    }

    @Bean
    fun binding(fanoutExchange: FanoutExchange, queue: Queue):Binding{
        return BindingBuilder.bind(queue).to(fanoutExchange)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}