package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.models.ProductInventory;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindOutOfStockProductsCommand extends CLICommand {

    private ProductsDAO productsDAO;

    public FindOutOfStockProductsCommand(ProductsDAO productsDAO) {
        super("stock");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        Collection<ProductInventory> outOfStockProducts = this.productsDAO.findOutOfStockProducts();

        System.out.println("Out of stock items: " + Arrays.toString(outOfStockProducts.toArray()));
    }
}
