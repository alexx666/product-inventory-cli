package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.models.ProductInventory;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindOutOfStockProductsHandler extends CommandHandler {
    private ProductsDAO productsDAO;

    public FindOutOfStockProductsHandler(ProductsDAO productsDAO, BufferedReader reader) {
        super(reader);
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle() throws Exception {
        Collection<ProductInventory> outOfStockProducts = this.productsDAO.findOutOfStockProducts();

        System.out.println("Out of stock items: " + Arrays.toString(outOfStockProducts.toArray()));
    }
}
