package com.alexx666.products.commands;

import com.alexx666.core.Command;

public class AddNewProduct extends Command {

    private String name;
    private String description;
    private int price;

    public AddNewProduct(String name, String description, int price) {
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

    public int getPrice() {
        return price;
    }
}
