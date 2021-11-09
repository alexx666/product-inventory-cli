package com.alexx666.core.products.Commands.Domain;

public class ProductRating {

    public static ProductRating create(int value) throws IllegalArgumentException {
        if (value > 5 || value < 1) {
            throw new IllegalArgumentException("Rating must be between 0 and 5. Rating provided " + value);
        }

        return new ProductRating(value);
    }

    private int value;

    private ProductRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
