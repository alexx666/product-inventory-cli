package com.alexx666.products.models;

import java.util.HashMap;
import java.util.Map;

// DDD Entity
public class Product {

    private String productId;
    private String productName;
    private Map<String, ProductRating> ratings;

    private Product(Builder builder) {
        this.productId = builder.productId;;
        this.productName = builder.productName;
        this.ratings = builder.ratings;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Map<String, ProductRating> getRatings() {
        return this.ratings;
    }

    public void rate(String userId, int rating) {
        this.ratings.put(userId, ProductRating.create(rating));
    }

    public static class Builder {
        private String productId;
        private String productName;
        private Map<String, ProductRating> ratings;

        public Builder() {
            this.ratings = new HashMap<>();
        }

        public Builder withRatings(Map<String, ProductRating> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder identifier(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder withName(String productName) {
            this.productName = productName;
            return this;
        }

        public Product build() {
            // TODO: validation logic
            return new Product(this);
        }
    }
}
