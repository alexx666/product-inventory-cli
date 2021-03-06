package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;
import com.alexx666.incli.products.presenters.ProductDisplayConsoleView;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.data.ProductsDAO;

import java.io.BufferedReader;
import java.util.Collection;

public class FindByNameCommand extends CLICommand {

    private final ProductsDAO productsDAO;

    public FindByNameCommand(ProductsDAO productsDAO) {
        super("search");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Name: ");
        String name = reader.readLine().trim();

        Collection<ProductDisplay> results = this.productsDAO.findByName(name);

        System.out.println("Matching results:");

        new ProductDisplayConsoleView().present(results);
    }
}
