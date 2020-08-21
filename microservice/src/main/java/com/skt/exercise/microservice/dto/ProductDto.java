package com.skt.exercise.microservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private int id;
    private String sku;
    private String name;
    private String description;
    private int units;
}
