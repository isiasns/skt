package com.skt.exercise.microservice.util;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.dto.ProductDto;

public class DtoMapper {
    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder().id(product.getId()).sku(product.getSku()).name(product.getName()).description(product.getDescription()).units(product.getUnits()).build();
    }
}
