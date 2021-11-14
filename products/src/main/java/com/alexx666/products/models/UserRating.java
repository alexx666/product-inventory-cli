package com.alexx666.products.models;

public class UserRating {

    private String productId;
    private String userId;
    private Rating rating;

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
