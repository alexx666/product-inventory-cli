package com.alexx666.incli.products.commands;

import com.alexx666.incli.cli.CLICommand;
import com.alexx666.incli.products.presenters.ProductDisplayConsoleView;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.data.ProductsDAO;

import java.io.BufferedReader;
import java.util.Collection;

public class FindRelatedProductsCommand extends CLICommand {

    private final ProductsDAO productsDAO;

    public FindRelatedProductsCommand(ProductsDAO productsDAO) {
        super("related");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Product ID: ");
        String productId = reader.readLine().trim();

        Collection<ProductDisplay> relatedProducts = this.productsDAO.findRelatedProducts(productId);

        System.out.println("Found these related products:");

        new ProductDisplayConsoleView().present(relatedProducts);
    }
}
