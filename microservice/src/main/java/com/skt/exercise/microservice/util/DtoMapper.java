package com.skt.exercise.microservice.util;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.dto.ProductDto;

public class DtoMapper {
    public static Product mapToPojo(ProductDto productDto) {
        return Product.builder().id(productDto.getId()).sku(productDto.getSku()).name(productDto.getName()).description(productDto.getDescription()).units(productDto.getUnits()).build();
    }
}
