package com.skt.exercise.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitMqConfig {
    public final static String PRODUCT_QUEUE_NAME = "product_queue";
    public final static String PRODUCT_LIST_QUEUE_NAME = "product_list_queue";
    public final static String PRODUCT_EXCHANGE_NAME = "product_exchange";

    @Bean
    Queue productQueue() {
        return new Queue(PRODUCT_QUEUE_NAME, false);
    }

    @Bean
    Queue productListQueue() {
        return new Queue(PRODUCT_LIST_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(PRODUCT_EXCHANGE_NAME);
    }

    @Bean
    Binding productBinding(Queue productQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(productQueue).to(topicExchange).with(productQueue.getName());
    }

    @Bean
    Binding productListBinding(Queue productListQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(productListQueue).to(topicExchange).with(productListQueue.getName());
    }
}
