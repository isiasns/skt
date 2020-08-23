package com.skt.exercise.microservice.service;

import com.skt.exercise.common.model.Product;

import java.util.List;

public interface ProductService {
    Long insertProduct(Product product);

    List<Product> getAllProducts();
}
