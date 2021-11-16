package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;

public class FindByIdCommand extends CLICommand {

    private ProductsDAO productsDAO;

    public FindByIdCommand(ProductsDAO productsDAO) {
        super("display");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Product ID: ");
        String productId = reader.readLine().trim();

        ProductDisplay productDisplay = this.productsDAO.findById(productId);

        System.out.println("    ID: " + productDisplay.getId());
        System.out.println("    Name: " + productDisplay.getName());
        System.out.println("    Description: " + productDisplay.getDescription());
        System.out.println("    Rating " + productDisplay.getUserRating() + " out of " + productDisplay.totalRatings() + " user(s)");
        System.out.println("    Price: " + productDisplay.getUnitPrice() + "$ per unit " + (productDisplay.isOutOfStock() ? "(out of stock)" : "(in stock)"));
    }
}
