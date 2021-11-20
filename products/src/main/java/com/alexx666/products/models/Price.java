package com.alexx666.products.models;

// Value Object
public final class Price {

    public static Price create(double value) throws IllegalArgumentException {
        if (value < 0.00) {
            throw new IllegalArgumentException("Price must be greater then $0.00. Price provided " + value);
        }

        return new Price(value);
    }

    private final double value;

    private Price(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
