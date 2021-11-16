package com.alexx666.products.models;

// Entity identified by productId and userId
// Could be saved to NoSQL database
public class UserRating {

    private final String productId;
    private final String userId;
    private final Rating rating;

    public UserRating(String productId, String userId, int rating) {
        this.productId = productId;
        this.userId = userId;
        this.rating = Rating.create(rating);
    }

    public String getUserId() {
        return userId;
    }

    public Rating getRating() {
        return rating;
    }

    public String getProductId() {
        return productId;
    }
}
