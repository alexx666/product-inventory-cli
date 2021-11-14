package com.alexx666.products.models;

public interface ProductRepository {
    Product find(String productId) throws Exception;
    String saveProduct(Product product);
    void saveUserRating(UserRating userRating);
}
