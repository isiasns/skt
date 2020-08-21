package com.skt.exercise.microservice.repository;

import com.skt.exercise.microservice.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto, Long> {
    @Procedure("insert_product")
    Long insertProduct(@Param("p_sku") String sku, @Param("p_name") String name, @Param("p_description") String description, @Param("p_units") Long units);
}
