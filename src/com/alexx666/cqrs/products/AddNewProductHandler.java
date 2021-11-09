package com.alexx666.cqrs.products;

import com.alexx666.cli.CommandHandler;

import com.alexx666.core.products.Commands.Application.AddNewProduct;
import com.alexx666.core.products.Commands.Domain.ProductRepository;

import java.io.BufferedReader;

public class AddNewProductHandler extends CommandHandler {

    public AddNewProductHandler(ProductRepository repository, BufferedReader reader) {
        super(repository, reader);
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