package com.alexx666.products;

import com.alexx666.products.commands.AddNewProduct;
import com.alexx666.products.commands.AddToInventory;
import com.alexx666.products.commands.RateProduct;

import com.alexx666.products.data.ProductRepository;
import com.alexx666.products.models.*;

public class ProductsCommandHandler {

    private final ProductRepository repository;

    public ProductsCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    // Clean Architecture variation could be each handle in a separate class
    public void handle(RateProduct command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        // Existence of user is responsibility of the auth service
        UserRating userRating = product.rate(command.getUserId(), command.getRating());

        this.repository.saveUserRating(userRating);
    }

    // This can be done emitting a domain event too
    public String handle(AddNewProduct command) {
        Product product = new Product.Builder()
                .withName(Name.create(command.getName()))
                .description(command.getDescription())
                .price(Price.create(command.getPrice()))
                .build();

        return this.repository.saveProduct(product);
    }

    // This can be done emitting a domain event too
    public void handle(AddToInventory command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        product.addToInventory(command.getNumberOfItems());

        this.repository.saveProduct(product);
    }
}
