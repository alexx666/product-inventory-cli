package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindRelatedProductsCommand extends CLICommand {

    private ProductsDAO productsDAO;

    public FindRelatedProductsCommand(ProductsDAO productsDAO) {
        super("findRelated");
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle(BufferedReader reader) throws Exception {
        System.out.print("Product ID: ");
        String productId = reader.readLine().trim();

        Collection<ProductDisplay> relatedProducts = this.productsDAO.findRelatedProducts(productId);

        System.out.println("Found these related products: " + Arrays.toString(relatedProducts.toArray()));
    }
}
