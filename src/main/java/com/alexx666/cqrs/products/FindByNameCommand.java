package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindByNameCommand extends CLICommand {

    private ProductsDAO productsDAO;

    public FindByNameCommand(ProductsDAO productsDAO) {
        super("search");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Name: ");
        String name = reader.readLine().trim();

        Collection<ProductDisplay> results = this.productsDAO.findByName(name);

        System.out.println("Matching results: " + Arrays.toString(results.toArray()));
    }
}
