package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;
import com.alexx666.incli.products.presenters.ProductInventoryConsoleView;
import com.alexx666.products.models.ProductInventory;
import com.alexx666.products.data.ProductsDAO;

import java.io.BufferedReader;
import java.util.Collection;

public class FindOutOfStockProductsCommand extends CLICommand {

    private final ProductsDAO productsDAO;

    public FindOutOfStockProductsCommand(ProductsDAO productsDAO) {
        super("stock");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        Collection<ProductInventory> outOfStockProducts = this.productsDAO.findOutOfStockProducts();

        new ProductInventoryConsoleView().present(outOfStockProducts);
    }
}
