package com.alexx666.products.models;

// DTO object
public class ProductDisplay {
    private String id;
    private String name;
    private String description;
    private double unitPrice;
    private boolean isOutOfStock;
    private double userRating;

    private ProductDisplay(Builder builder) {
        this.id = builder.id;;
        this.name = builder.name;
        this.description = builder.description;
        this.unitPrice = builder.unitPrice;
        this.isOutOfStock = builder.isOutOfStock;
        this.userRating = builder.userRating;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }

    public double getUserRating() {
        return userRating;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private double unitPrice;
        private boolean isOutOfStock;
        private double userRating;

        public Builder identifier(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder outOfStock(boolean outOfStock) {
            isOutOfStock = outOfStock;
            return this;
        }

        public Builder rating(double userRating) {
            this.userRating = userRating;
            return this;
        }

        public ProductDisplay build() {
            return new ProductDisplay(this);
        }
    }
}
