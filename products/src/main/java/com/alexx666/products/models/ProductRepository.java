package com.alexx666.products.models;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    void save(Product product);
}
