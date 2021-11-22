package com.alexx666.products.models;

public final class Name {
    private final String value;

    // other more sophisticated validations can be implemented
    public static Name create(String value) {
        if (value.length() < 1 || value.length() > 20) {
            throw new IllegalArgumentException("Name should be between 1 and 20 characters!");
        }

        return new Name(value);
    }

    private Name(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
