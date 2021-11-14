package com.alexx666.products.models;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    String save(Product product);
}
