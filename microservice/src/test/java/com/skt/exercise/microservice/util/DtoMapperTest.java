package com.skt.exercise.microservice.util;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.dto.ProductDto;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DtoMapperTest {

    @Test
    public void givenProductDtoWhenMapToPojoReturnProduct() {
        ProductDto productDto = ProductDto.builder().build();
        Product result = DtoMapper.mapToPojo(productDto);
        assertNotNull(result);
    }
}
