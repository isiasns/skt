package com.skt.exercise.microservice.service;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long insertProduct(Product product) {
        return productRepository.insertProduct(product.getSku(), product.getName(), product.getDescription(), product.getUnits());
    }
}
