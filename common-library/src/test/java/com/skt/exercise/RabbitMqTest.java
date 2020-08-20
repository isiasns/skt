package com.skt.exercise;

import com.skt.exercise.common.config.ProductRabbitMqConfig;
import com.skt.exercise.common.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductRabbitMqConfig.class})
@EnableAutoConfiguration
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME, Product.builder().id(1).sku("sku").description("description").units(1).build());
        Message message = rabbitTemplate.receive(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME);
        assertNotNull(message);
    }
}
