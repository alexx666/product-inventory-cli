package com.alexx666.cqrs.products;

import com.alexx666.cli.CommandHandler;

import com.alexx666.core.products.Commands.Application.RateProduct;
import com.alexx666.core.products.ProductsCommandHandler;

import java.io.BufferedReader;

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
        String userID = this.reader.readLine().trim();

        System.out.print("Rating: ");
        int rating = Integer.parseInt(this.reader.readLine().trim());

        RateProduct command = new RateProduct(productId, userID, rating);

        System.out.println("Executing request ID: " + command.getUUID());

        double totalRating = handlers.handle(command);

        System.out.println("Total rating: " + totalRating);
    }
}