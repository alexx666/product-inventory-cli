package com.alexx666.products.models;

public interface ProductRepository {
    /**
     * Query function that obtains a Product given its ID
     *
     * @param productId String representing the objects unique identifier (UUID)
     * @return {@link Product} object
     * @throws Exception if the object is not found or there has been some sort of problem with the query
     */
    Product find(String productId) throws Exception;

    /**
     * Saves a {@link Product} object, new or existing, to the database and returns its unique identifier (UUID).
     * Serves as both create and update.
     *
     * @param product {@link Product} object to be saved. Can be new or existing.
     * @return String value of the UUID
     */
    String saveProduct(Product product);

    /**
     * Saves a {@link UserRating} to the database
     *
     * @param userRating {@link UserRating} object to be saved
     */
    void saveUserRating(UserRating userRating);
}
