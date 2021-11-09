package com.alexx666.cqrs.products;

import com.alexx666.cli.CommandHandler;

import com.alexx666.core.products.Commands.Application.AddNewProduct;
import com.alexx666.core.products.ProductsCommandHandler;

import java.io.BufferedReader;

public class AddNewProductHandler extends CommandHandler {

    private ProductsCommandHandler handlers;

    public AddNewProductHandler(ProductsCommandHandler handlers, BufferedReader reader) {
        super(reader);

        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product Name: ");
        String productName = this.reader.readLine().trim();

        AddNewProduct command = new AddNewProduct(productName);

        System.out.println("Executing request ID: " + command.getUUID());

        String productId = handlers.handle(command);

        System.out.println("New product added with ID: " + productId);
    }
}
