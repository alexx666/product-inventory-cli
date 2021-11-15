package com.alexx666.cqrs;

import com.alexx666.cqrs.products.*;
import com.alexx666.cqrs.cli.CLI;
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
                .addCommand(new RateProductHandler(productsCommandHandler))
                .addCommand(new AddNewProductHandler(productsCommandHandler))
                .addCommand(new AddToInventoryHandler(productsCommandHandler))
                .addCommand(new FindByIdHandler(productsDAO))
                .addCommand(new FindRelatedProductsHandler(productsDAO))
                .addCommand(new FindOutOfStockProductsHandler(productsDAO))
                .addCommand(new FindByNameHandler(productsDAO))
                .build();

        cli.showHelp();

        // FIXME: temporary infinite loop to maintain data in memory
        while (true) {
            cli.run();
        }
    }
}
