package com.alexx666.cqrs;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.cqrs.products.*;
import com.alexx666.cqrs.cli.CLI;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.infra.InMemoryProductDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        InMemoryProductDatabase database = new InMemoryProductDatabase.Builder().build();
        ProductsCommandHandler productsCommandHandler = new ProductsCommandHandler(database);

        // FIXME: remove reader from constructor
        CLICommand rateProductHandler = new RateProductHandler(productsCommandHandler, reader);
        CLICommand addNewProductHandler = new AddNewProductHandler(productsCommandHandler, reader);
        CLICommand addToInventoryHandler = new AddToInventoryHandler(productsCommandHandler, reader);
        CLICommand findByIdHandler = new FindByIdHandler(database, reader);
        CLICommand findRelatedProductsHandler = new FindRelatedProductsHandler(database, reader);
        CLICommand findOutOfStockProductsHandler = new FindOutOfStockProductsHandler(database, reader);
        CLICommand findByNameHandler = new FindByNameHandler(database, reader);

        CLI commandParser = new CLI.Builder()
                .input(reader)
                .addCommand("rate", rateProductHandler)
                .addCommand("new", addNewProductHandler)
                .addCommand("add", addToInventoryHandler)
                .addCommand("findById", findByIdHandler)
                .addCommand("findRelated", findRelatedProductsHandler)
                .addCommand("findOutOfStock", findOutOfStockProductsHandler)
                .addCommand("findByName", findByNameHandler)
                .build();

        try {
            commandParser.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
