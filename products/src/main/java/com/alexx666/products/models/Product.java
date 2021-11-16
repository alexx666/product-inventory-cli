package com.alexx666.products.models;

// DDD Entity
public class Product {

    private String productId;
    private String productName; // TODO: V.O.
    private String description;
    private Price price;
    private int itemsInStock;

    private Product(Builder builder) {
        this.productId = builder.productId;;
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
        private String productId;
        private String productName; // TODO: V.O.
        private String description;
        private Price price;
        private int itemsInStock;

        public Builder() {
            this.itemsInStock = 0;
        }

        public Builder identifier(String productId) {
            this.productId = productId;
            return this;
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

        public Builder inStock(int count) {
            this.itemsInStock = count;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
