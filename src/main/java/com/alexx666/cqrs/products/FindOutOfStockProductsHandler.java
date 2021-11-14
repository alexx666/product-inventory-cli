package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.ProductsQueryHandler;
import com.alexx666.products.models.ProductInventory;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindOutOfStockProductsHandler extends CommandHandler {
    private ProductsQueryHandler handlers;

    public FindOutOfStockProductsHandler(ProductsQueryHandler handlers, BufferedReader reader) {
        super(reader);
        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        Collection<ProductInventory> outOfStockProducts = this.handlers.findOutOfStockProducts();

        System.out.println("Out of stock items: " + Arrays.toString(outOfStockProducts.toArray()));
    }
}
