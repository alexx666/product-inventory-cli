package com.alexx666.incli;

import com.alexx666.incli.cli.CLI;
import com.alexx666.incli.products.commands.*;
import com.alexx666.products.ProductsCommandHandler;
import com.alexx666.products.infra.mem.InMemoryProductDAO;
import com.alexx666.products.infra.mem.InMemoryProductRepository;
import com.alexx666.products.models.Product;
import com.alexx666.products.data.ProductRepository;
import com.alexx666.products.data.ProductsDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
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
                .input(new BufferedReader(new InputStreamReader(System.in)))
                .addCommand(new RateProductCommand(productsCommandHandler))
                .addCommand(new AddNewProductCommand(productsCommandHandler))
                .addCommand(new AddToInventoryCommand(productsCommandHandler))
                .addCommand(new FindByIdCommand(productsDAO))
                .addCommand(new FindRelatedProductsCommand(productsDAO))
                .addCommand(new FindOutOfStockProductsCommand(productsDAO))
                .addCommand(new FindByNameCommand(productsDAO))
                .build();

        // FIXME: temporary infinite loop to maintain data in memory
        while (true) {
            cli.run();
        }
    }
}
