package com.alexx666.products.commands;

import com.alexx666.core.Command;

public class AddNewProduct extends Command {

    private String name;
    private String description;
    private double price;
    private int count;

    public AddNewProduct(String name, String description, double price, int count) {
        super();

        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
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

    public int getCount() {
        return count;
    }
}
