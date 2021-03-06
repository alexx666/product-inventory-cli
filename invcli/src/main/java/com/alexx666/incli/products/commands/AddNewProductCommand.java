package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;

import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddNewProduct;

import java.io.BufferedReader;

public class AddNewProductCommand extends CLICommand {

    private final ProductsCommandHandler handlers;

    public AddNewProductCommand(ProductsCommandHandler handlers) {
        super("new");

        this.handlers = handlers;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Name: ");
        String productName = reader.readLine().trim();

        System.out.print("Description: ");
        String description = reader.readLine().trim();

        System.out.print("Price: ");
        double price = Double.parseDouble(reader.readLine().trim());

        AddNewProduct command = new AddNewProduct(productName, description, price);
        System.out.println("Executing request ID: " + command.getUUID());

        String productId = handlers.handle(command);

        System.out.println("New product '" + productName + "' (" + productId + ") added!");
    }
}
