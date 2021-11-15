package com.alexx666.cqrs;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.cqrs.products.*;
import com.alexx666.cqrs.cli.CLI;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.infra.InMemoryProductDAO;
import com.alexx666.products.infra.InMemoryProductRepository;
import com.alexx666.products.models.Product;
import com.alexx666.products.models.ProductRepository;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Product> productData = new HashMap<>();
        Map<String, Map<String, Integer>> userRatingsData = new HashMap<>();

        ProductRepository productRepository = new InMemoryProductRepository.Builder()
                .initialProducts(productData)
                .initialRatings(userRatingsData)
                .build();

        ProductsDAO productsDAO = new InMemoryProductDAO.Builder()
                .initialProducts(productData)
                .initialRatings(userRatingsData)
                .build();

        ProductsCommandHandler productsCommandHandler = new ProductsCommandHandler(productRepository);

        // FIXME: remove reader from constructor
        CLICommand rateProductHandler = new RateProductHandler(productsCommandHandler, reader);
        CLICommand addNewProductHandler = new AddNewProductHandler(productsCommandHandler, reader);
        CLICommand addToInventoryHandler = new AddToInventoryHandler(productsCommandHandler, reader);
        CLICommand findByIdHandler = new FindByIdHandler(productsDAO, reader);
        CLICommand findRelatedProductsHandler = new FindRelatedProductsHandler(productsDAO, reader);
        CLICommand findOutOfStockProductsHandler = new FindOutOfStockProductsHandler(productsDAO, reader);
        CLICommand findByNameHandler = new FindByNameHandler(productsDAO, reader);

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
