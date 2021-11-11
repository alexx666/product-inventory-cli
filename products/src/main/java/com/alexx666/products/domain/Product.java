package com.alexx666.products.domain;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private String productId;
    private String productName;
    private Map<String, ProductRating> ratings;

    public Product(String productId, String productName, Map<String, ProductRating> ratings) {
        this.productId = productId;
        this.productName = productName;
        this.ratings = ratings;
    }

    public Product(String productName) {
        this.productName = productName;
        this.ratings = new HashMap<>();
    }

    public void rate(String userId, ProductRating rating) {
        this.ratings.put(userId, rating);
    }

    public String getProductId() {
        return this.productId;
    }

    public double getTotalRating() {
        double totalRating = 0;

        for (ProductRating rating: this.ratings.values()) {
            totalRating += rating.getValue();
        }

        return (double) Math.round(totalRating / this.getNumberOfRatings() * 10) / 10;
    }

    public int getNumberOfRatings() {
        return this.ratings.entrySet().size();
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductId(String id) {
        this.productId = id;
    }
}
