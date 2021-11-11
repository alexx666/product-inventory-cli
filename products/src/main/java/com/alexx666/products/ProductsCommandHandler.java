package com.alexx666.products;

import com.alexx666.products.commands.AddNewProduct;
import com.alexx666.products.models.Product;
import com.alexx666.products.commands.RateProduct;
import com.alexx666.products.models.ProductRating;
import com.alexx666.products.models.ProductRepository;

public class ProductsCommandHandler {

    private ProductRepository repository;

    public ProductsCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    // FIXME: use a response model to represent the result of the operation
    // Clean Architecture variation could be each handle in a separate class
    public void handle(RateProduct command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        ProductRating rating = ProductRating.create(command.getRating());

        product.rate(command.getUserId(), rating);

        this.repository.save(product);
    }

    // FIXME: use a response model to represent the result of the operation
    public String handle(AddNewProduct command) {
        Product product = new Product(command.getName());

        this.repository.save(product);

        return product.getProductId();
    }
}
