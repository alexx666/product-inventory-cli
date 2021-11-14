package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;

import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.commands.AddNewProduct;

import java.io.BufferedReader;

public class AddNewProductHandler extends CommandHandler {

    private ProductsCommandHandler handlers;

    public AddNewProductHandler(ProductsCommandHandler handlers, BufferedReader reader) {
        super(reader);

        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Name: ");
        String productName = this.reader.readLine().trim();

        System.out.print("Description: ");
        String description = this.reader.readLine().trim();

        System.out.print("Price: ");
        double price = Double.parseDouble(this.reader.readLine().trim());

        System.out.print("Items in stock: ");
        int count = Integer.parseInt(this.reader.readLine().trim());

        AddNewProduct command = new AddNewProduct(productName, description, price, count);
        System.out.println("Executing request ID: " + command.getUUID());

        String productId = handlers.handle(command);

        System.out.println("New product '" + productName + "' (" + productId + ") added!");
    }
}
