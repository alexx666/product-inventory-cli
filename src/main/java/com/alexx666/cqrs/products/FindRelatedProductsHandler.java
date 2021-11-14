package com.alexx666.cqrs.products;

import com.alexx666.cqrs.utils.CommandHandler;
import com.alexx666.products.ProductsQueryHandler;
import com.alexx666.products.models.ProductDisplay;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

public class FindRelatedProductsHandler extends CommandHandler {

    private ProductsQueryHandler handlers;

    public FindRelatedProductsHandler(ProductsQueryHandler handlers, BufferedReader reader) {
        super(reader);
        this.handlers = handlers;
    }

    @Override
    public void handle() throws Exception {
        System.out.print("Product ID: ");
        String productId = this.reader.readLine();

        Collection<ProductDisplay> relatedProducts = this.handlers.findRelatedProducts(productId);

        System.out.println("Found these related products: " + Arrays.toString(relatedProducts.toArray()));
    }
}
