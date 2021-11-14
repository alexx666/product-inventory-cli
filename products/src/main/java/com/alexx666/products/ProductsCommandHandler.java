package com.alexx666.products;

import com.alexx666.products.commands.AddNewProduct;
import com.alexx666.products.commands.RateProduct;

import com.alexx666.products.models.*;

public class ProductsCommandHandler {

    private ProductRepository repository;

    public ProductsCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    // FIXME: use a response model to represent the result of the operation
    // Clean Architecture variation could be each handle in a separate class
    public void handle(RateProduct command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        // Existence of user is responsibility of the auth service
        UserRating userRating = product.rate(command.getUserId(), command.getRating());

        this.repository.saveUserRating(userRating);
    }

    // FIXME: use a response model to represent the result of the operation
    public String handle(AddNewProduct command) {
        Product product = new Product.Builder()
                .withName(command.getName())
                .build();

        return this.repository.saveProduct(product);
    }
}
