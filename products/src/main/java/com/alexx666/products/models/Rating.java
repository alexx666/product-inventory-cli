package com.alexx666.products.models;

// Value Object
public class Rating {

    public static Rating create(int value) throws IllegalArgumentException {
        if (value > 5 || value < 1) {
            throw new IllegalArgumentException("Rating must be between 0 and 5. Rating provided " + value);
        }

        return new Rating(value);
    }

    private int value;

    private Rating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
