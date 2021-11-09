package com.alexx666.core.products.Commands.Domain;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    void save(Product product);
}
