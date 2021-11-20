package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;
import com.alexx666.incli.products.presenters.ProductDisplayConsoleView;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.data.ProductsDAO;

import java.io.BufferedReader;

public class FindByIdCommand extends CLICommand {

    private final ProductsDAO productsDAO;

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
