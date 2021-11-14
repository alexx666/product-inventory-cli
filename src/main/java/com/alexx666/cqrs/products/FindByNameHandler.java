package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindByNameHandler extends CommandHandler {
    private ProductsDAO productsDAO;

    public FindByNameHandler(ProductsDAO productsDAO, BufferedReader reader) {
        super(reader);
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Name: ");
        String name = this.reader.readLine();

        Collection<ProductDisplay> results = this.productsDAO.findByName(name);

        System.out.println("Matching results: " + Arrays.toString(results.toArray()));
    }
}
