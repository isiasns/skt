package com.skt.exercise.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String sku;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Long units;
}
