package com.alexx666.products.commands;

import com.alexx666.core.cqrs.Command;

public class AddNewProduct extends Command {

    private final String name;
    private final String description;
    private final double price;

    public AddNewProduct(String name, String description, double price) {
        super();

        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
