package com.skt.exercise.microservice.util;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.dto.ProductDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DtoMapperTest {

    @Test
    public void givenProductWhenMapToDtoThenReturnProductDTO() {
        Product product = Product.builder().id(1).sku("sku").name("name").description("description").units(1).build();
        ProductDto productDto = DtoMapper.mapToDto(product);
        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getSku(), productDto.getSku());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertEquals(product.getUnits(), productDto.getUnits());
    }
}
