package com.alexx666.products.commands;

import com.alexx666.core.cqrs.Command;

public class AddToInventory extends Command {

    private final String productId;
    private final int numberOfItems;

    public AddToInventory(String productId, int numberOfItems) {
        super();
        this.productId = productId;
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public String getProductId() {
        return productId;
    }
}
