package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddToInventory;

import java.io.BufferedReader;

public class AddToInventoryHandler extends CLICommand {

    private ProductsCommandHandler handlers;

    public AddToInventoryHandler(ProductsCommandHandler handlers) {
        super("add");
        this.handlers = handlers;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Product ID: ");
        String productId = reader.readLine().trim();

        System.out.print("How many items? ");
        int count = Integer.parseInt(reader.readLine().trim());

        AddToInventory command = new AddToInventory(productId, count);
        System.out.println("Executing request ID: " + command.getUUID());

        this.handlers.handle(command);

        System.out.println("Added " + count + " items of product " + productId + " to the inventory!");
    }
}
