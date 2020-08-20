package com.skt.exercise;

import com.skt.exercise.common.config.ProductRabbitMqConfig;
import com.skt.exercise.common.model.Product;
import com.skt.exercise.common.model.Products;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductRabbitMqConfig.class})
@EnableAutoConfiguration
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void givenProductWhenSendToProductQueueThenMessageIsNotNull() {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME, Product.builder().id(1).sku("sku").description("description").units(1).build());
        Message message = rabbitTemplate.receive(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME);
        System.out.println(message);
        assertNotNull(message);
    }

    @Test
    public void givenProductListWhenSendToProductListQueueThenMessageIsNotNull() {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME, Products.builder().productList(Collections.emptyList()).build());
        Message message = rabbitTemplate.receive(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME);
        System.out.println(message);
        assertNotNull(message);
    }

    @Test
    public void givenProductWhenSendToProductQueueThenMessageBodyIsJson() throws JSONException {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME, Product.builder().id(1).sku("sku").description("description").units(1).build());
        Message message = rabbitTemplate.receive(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME);
        JSONAssert.assertEquals("{\"id\":1,\"sku\":\"sku\",\"description\":\"description\",\"units\":1}", new String(message.getBody()), JSONCompareMode.STRICT);
    }

    @Test
    public void givenProductListWhenSendToProductListQueueThenMessageBodyIsJson() throws JSONException {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME, Products.builder().productList(Collections.emptyList()).build());
        Message message = rabbitTemplate.receive(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME);
        JSONAssert.assertEquals("{\"productList\":[]}", new String(message.getBody()), JSONCompareMode.STRICT);
    }

    @Test
    public void givenProductWhenSendToProductQueueThenMessageBodyIsProduct() {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME, Product.builder().id(1).sku("sku").description("description").units(1).build());
        Product product = (Product) rabbitTemplate.receiveAndConvert(ProductRabbitMqConfig.PRODUCT_QUEUE_NAME);
        assertEquals(1, product.getId());
    }

    @Test
    public void givenProductListWhenSendToProductListQueueThenMessageBodyIsProductList() {
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME, Products.builder().productList(Collections.emptyList()).build());
        Products products = (Products) rabbitTemplate.receiveAndConvert(ProductRabbitMqConfig.PRODUCT_LIST_QUEUE_NAME);
        assertEquals(Collections.emptyList(), products.getProductList());
    }
}
