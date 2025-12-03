package com.project.api_gateway.product_service.Repository;

import com.project.api_gateway.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
