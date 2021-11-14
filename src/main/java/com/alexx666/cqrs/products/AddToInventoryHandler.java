package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddToInventory;

import java.io.BufferedReader;

public class AddToInventoryHandler extends CommandHandler {

    private ProductsCommandHandler handlers;

    public AddToInventoryHandler(ProductsCommandHandler handlers, BufferedReader reader) {
        super(reader);
        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product ID: ");
        String productId = this.reader.readLine().trim();

        System.out.print("How many items? ");
        int count = Integer.parseInt(this.reader.readLine().trim());

        AddToInventory command = new AddToInventory(productId, count);

        this.handlers.handle(command);

        System.out.println("Added " + count + " items of product " + productId + " to the inventory!");
    }
}
