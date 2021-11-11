package com.alexx666.products.commands;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    void save(Product product);
}
