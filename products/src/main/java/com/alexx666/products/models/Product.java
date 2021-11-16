package com.alexx666.products.models;

// DDD Entity
public class Product {

    private String productId;
    private final String productName; // TODO: V.O.
    private final String description;
    private final Price price;
    private int itemsInStock;

    private Product(Builder builder) {
        this.productName = builder.productName;
        this.description = builder.description;
        this.price = builder.price;
        this.itemsInStock = builder.itemsInStock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String id) {
        this.productId = id;
    }

    public boolean isNew() {
        return this.productId == null;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price.getValue();
    }

    public String getDescription() {
        return description;
    }

    public int getItemsInStock() {
        return itemsInStock;
    }

    public void addToInventory(int count) {
        this.itemsInStock += count;
    }

    // action on the domain object that can be a lot more complex
    public UserRating rate(String userId, int rating) {
        return new UserRating(this.productId, userId, rating);
    }

    public static final class Builder {
        private String productName; // TODO: V.O.
        private String description;
        private Price price;
        private final int itemsInStock;

        public Builder() {
            this.itemsInStock = 0;
        }

        public Builder withName(String productName) {
            this.productName = productName; // TODO: convert to VO here
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = Price.create(price);
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
