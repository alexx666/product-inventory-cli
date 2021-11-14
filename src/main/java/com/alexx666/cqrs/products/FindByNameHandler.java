package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.ProductsQueryHandler;
import com.alexx666.products.models.ProductDisplay;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindByNameHandler extends CommandHandler {
    private ProductsQueryHandler handlers;

    public FindByNameHandler(ProductsQueryHandler handlers, BufferedReader reader) {
        super(reader);
        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Name: ");
        String name = this.reader.readLine();

        Collection<ProductDisplay> results = this.handlers.findByName(name);

        System.out.println("Matching results: " + Arrays.toString(results.toArray()));
    }
}
