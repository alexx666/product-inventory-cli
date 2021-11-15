package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;

import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.RateProduct;

import java.io.BufferedReader;

// A run of the mill controller for a CLI context
public class RateProductCommand extends CLICommand {

    private ProductsCommandHandler handlers;

    public RateProductCommand(ProductsCommandHandler handlers) {
        super("rate");
        this.handlers = handlers;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Product Id: ");
        String productId = reader.readLine().trim();

        System.out.print("User Id: ");
        String userId = reader.readLine().trim();

        System.out.print("Rating: ");
        int rating = Integer.parseInt(reader.readLine().trim());

        RateProduct command = new RateProduct(productId, userId, rating);
        System.out.println("Executing request ID: " + command.getUUID());

        handlers.handle(command);

        System.out.println("User (" + userId + ") rated product (" + productId + ") with '" + command.getRating() + "' stars!");
    }
}
