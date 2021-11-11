package com.alexx666.products.commands;

import com.alexx666.core.Command;

public class AddNewProduct extends Command {

    private String name;

    public AddNewProduct(String name) {
        super();

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
