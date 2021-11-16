package com.alexx666.cqrs;

import com.alexx666.cqrs.cli.CLI;
import com.alexx666.cqrs.products.commands.*;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.infra.InMemoryProductDAO;
import com.alexx666.products.infra.InMemoryProductRepository;
import com.alexx666.products.models.Product;
import com.alexx666.products.models.ProductRepository;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
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

        CLI cli = new CLI.Builder()
                .input(reader)
                .addCommand(new RateProductCommand(productsCommandHandler))
                .addCommand(new AddNewProductCommand(productsCommandHandler))
                .addCommand(new AddToInventoryCommand(productsCommandHandler))
                .addCommand(new FindByIdCommand(productsDAO))
                //.addCommand(new FindRelatedProductsCommand(productsDAO)) // TODO: activate when implemented
                .addCommand(new FindOutOfStockProductsCommand(productsDAO))
                //.addCommand(new FindByNameCommand(productsDAO)) // TODO: activate when implemented
                .build();

        // FIXME: temporary infinite loop to maintain data in memory
        while (true) {
            cli.run();
        }
    }
}
