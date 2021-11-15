package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;

import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddNewProduct;

import java.io.BufferedReader;

public class AddNewProductHandler extends CLICommand {

    private ProductsCommandHandler handlers;

    public AddNewProductHandler(ProductsCommandHandler handlers) {
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

        System.out.print("Items in stock: ");
        int count = Integer.parseInt(reader.readLine().trim());

        AddNewProduct command = new AddNewProduct(productName, description, price, count);
        System.out.println("Executing request ID: " + command.getUUID());

        String productId = handlers.handle(command);

        System.out.println("New product '" + productName + "' (" + productId + ") added!");
    }
}
