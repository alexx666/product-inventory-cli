package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddToInventory;

import java.io.BufferedReader;

public class AddToInventoryCommand extends CLICommand {

    private final ProductsCommandHandler handlers;

    public AddToInventoryCommand(ProductsCommandHandler handlers) {
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
