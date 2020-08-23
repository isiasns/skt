package com.skt.exercise.microservice.service;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.dto.ProductDto;
import com.skt.exercise.microservice.repository.ProductRepository;
import com.skt.exercise.microservice.util.DtoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({DtoMapper.class})
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {

        @Autowired
        private ProductRepository productRepository;

        @Bean
        public ProductService productService() {
            return new DefaultProductService(productRepository);
        }
    }

    @Test
    public void givenProductWhenInsertProductThenReturnId() {
        Product product = Product.builder().id(1L).sku("sku").name("name").description("description").units(1L).build();
        when(productRepository.insertProduct(any(String.class), any(String.class), any(String.class), any(Long.class))).thenReturn(1L);
        Long result = productService.insertProduct(product);
        assertNotEquals(0L, result.longValue());
    }

    @Test
    public void whenGetAllProductsThenReturnProductList() {
        Product product = Product.builder().id(1L).sku("sku").name("name").description("description").units(1L).build();
        ProductDto productDto = ProductDto.builder().id(1L).sku("sku").name("name").description("description").units(1L).build();
        mockStatic(DtoMapper.class);
        PowerMockito.when(productRepository.getAllProducts()).thenReturn(Collections.singletonList(productDto));
        PowerMockito.when(DtoMapper.mapToPojo(any(ProductDto.class))).thenReturn(product);
        List<Product> result = productService.getAllProducts();
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }
}
