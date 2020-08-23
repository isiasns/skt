package com.skt.exercise.microservice.service;

import com.skt.exercise.common.model.Product;
import com.skt.exercise.microservice.repository.ProductRepository;
import com.skt.exercise.microservice.util.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts().stream().map(DtoMapper::mapToPojo).collect(Collectors.toList());
    }
}
