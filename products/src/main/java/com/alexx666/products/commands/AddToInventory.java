package com.alexx666.products.commands;

import com.alexx666.core.Command;

public class AddToInventory extends Command {

    private String productId;
    private int numberOfItems;

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
