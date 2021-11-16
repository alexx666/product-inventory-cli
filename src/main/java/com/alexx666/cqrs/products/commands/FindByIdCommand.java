package com.alexx666.cqrs.products.commands;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.cqrs.products.presenters.ProductDisplayConsoleView;
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

        new ProductDisplayConsoleView().present(productDisplay);
    }
}
