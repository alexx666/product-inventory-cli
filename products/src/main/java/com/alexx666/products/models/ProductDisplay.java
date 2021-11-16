package com.alexx666.products.models;

// DTO object
public class ProductDisplay {
    private final String id;
    private final String name;
    private final String description;
    private final double unitPrice;
    private final boolean isOutOfStock;
    private final double userRating;
    private final int totalRatings;

    private ProductDisplay(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.unitPrice = builder.unitPrice;
        this.isOutOfStock = builder.isOutOfStock;
        this.userRating = builder.userRating;
        this.totalRatings = builder.totalRatings;
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

    public int totalRatings() { return totalRatings; }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private double unitPrice;
        private boolean isOutOfStock;
        private double userRating;
        private int totalRatings;

        public Builder() {
            this.totalRatings = 0;
            this.userRating = 0.0;
        }

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

        public Builder totalRatings(int totalRatings) {
            this.totalRatings = totalRatings;
            return this;
        }

        public ProductDisplay build() {
            return new ProductDisplay(this);
        }
    }
}
