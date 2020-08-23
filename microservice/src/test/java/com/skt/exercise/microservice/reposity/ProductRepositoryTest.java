package com.skt.exercise.microservice.reposity;

import com.skt.exercise.microservice.dto.ProductDto;
import com.skt.exercise.microservice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProductDtoWhenInsertProductThenReturnProductDtoWithId() {
        ProductDto productDto = ProductDto.builder().sku("sku").name("name").description("description").units(1L).build();
        Long result = productRepository.insertProduct(productDto.getSku(), productDto.getName(), productDto.getDescription(), productDto.getUnits());
        assertNotEquals(0, result.intValue());
    }

    @Test
    public void whenGetAllProductsThenReturnListOfProducts() {
        entityManager.persist(ProductDto.builder().sku("sku").name("name").description("description").units(0L).build());
        List<ProductDto> dtoList = productRepository.getAllProducts();
        assertEquals(1, dtoList.size());
    }
}
