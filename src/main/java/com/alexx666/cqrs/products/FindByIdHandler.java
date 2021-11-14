package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;

public class FindByIdHandler extends CommandHandler {

    private ProductsDAO productsDAO;

    public FindByIdHandler(ProductsDAO productsDAO, BufferedReader reader) {
        super(reader);
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product ID: ");
        String productId = this.reader.readLine();

        ProductDisplay productDisplay = this.productsDAO.findById(productId);

        System.out.println("    ID: " + productDisplay.getId());
        System.out.println("    Name: " + productDisplay.getName());
        System.out.println("    Description: " + productDisplay.getDescription());
        System.out.println("    Rating " + productDisplay.getUserRating() + " out of " + productDisplay.totalRatings() + " user(s)");
        System.out.println("    Price: " + productDisplay.getUnitPrice() + "$ per unit " + (productDisplay.isOutOfStock() ? "(out of stock)" : "(in stock)"));
    }
}
