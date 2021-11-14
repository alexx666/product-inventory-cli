package com.alexx666.products.models;

// DDD Entity
public class Product {

    private String productId;
    private String productName; // TODO: V.O.
    private String description;
    private double price; // TODO: V.O.

    private Product(Builder builder) {
        this.productId = builder.productId;;
        this.productName = builder.productName;
        this.description = builder.description;
        this.price = builder.price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // action on the domain object that can be a lot more complex
    public UserRating rate(String userId, int rating) {
        return new UserRating(this.productId, userId, rating);
    }

    public static class Builder {
        private String productId;
        private String productName;
        private String description;
        private double price; // TODO: V.O.

        public Builder identifier(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder withName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price; // TODO: convert to VO here
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
