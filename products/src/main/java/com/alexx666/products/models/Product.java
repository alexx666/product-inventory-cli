package com.alexx666.products.models;

// DDD Root Entity for the aggregate
public class Product {

    private String productId;
    private final Name productName;
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

    public Name getProductName() {
        return productName;
    }

    public Price getPrice() {
        return price;
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
        private Name productName;
        private String description;
        private Price price;
        private final int itemsInStock;

        /**
         * {@link Product} builder
         */
        public Builder() {
            this.itemsInStock = 0;
        }

        public Builder withName(Name productName) {
            this.productName = productName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(Price price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
