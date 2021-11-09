package com.alexx666.core.products.Commands.Application;

import com.alexx666.core.utils.Command;

public class AddNewProduct extends Command {

    private String name;

    // TODO: convert primitives to value objects
    public AddNewProduct(String name) {
        super();

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
