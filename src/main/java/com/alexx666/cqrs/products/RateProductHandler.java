package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;

import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.RateProduct;

import java.io.BufferedReader;

// A run of the mill controller for a CLI context
public class RateProductHandler extends CommandHandler {

    private ProductsCommandHandler handlers;

    public RateProductHandler(ProductsCommandHandler handlers, BufferedReader reader) {
        super(reader);
        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product Id: ");
        String productId = this.reader.readLine().trim();

        System.out.print("User Id: ");
        String userId = this.reader.readLine().trim();

        System.out.print("Rating: ");
        int rating = Integer.parseInt(this.reader.readLine().trim());

        RateProduct command = new RateProduct(productId, userId, rating);

        System.out.println("Executing request ID: " + command.getUUID());

        handlers.handle(command);

        System.out.println("User (" + userId + ") rated product (" + productId + ") with '" + command.getRating() + "' stars!");
    }
}
