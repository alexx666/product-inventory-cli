package com.alexx666.cqrs.products;

import com.alexx666.cqrs.cli.CLICommand;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductsDAO;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindRelatedProductsHandler extends CLICommand {

    private ProductsDAO productsDAO;

    public FindRelatedProductsHandler(ProductsDAO productsDAO, BufferedReader reader) {
        super(reader);
        this.productsDAO = productsDAO;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product ID: ");
        String productId = this.reader.readLine().trim();

        Collection<ProductDisplay> relatedProducts = this.productsDAO.findRelatedProducts(productId);

        System.out.println("Found these related products: " + Arrays.toString(relatedProducts.toArray()));
    }
}
