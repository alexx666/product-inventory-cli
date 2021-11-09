package com.alexx666.core.products;

import com.alexx666.core.products.Commands.Application.AddNewProduct;
import com.alexx666.core.products.Commands.Domain.Product;
import com.alexx666.core.products.Commands.Application.RateProduct;
import com.alexx666.core.products.Commands.Domain.ProductRepository;

public class ProductsCommandHandler {

    private ProductRepository repository;

    public ProductsCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    // FIXME: use a response model to represent the result of the operation
    public double handle(RateProduct command) throws Exception {
        Product product = this.repository.find(command.getProductId());

        product.rate(command.getUserId(), command.getRating());

        this.repository.save(product);

        return product.getTotalRating();
    }

    // FIXME: use a response model to represent the result of the operation
    public String handle(AddNewProduct command) {
        Product product = new Product(command.getName());

        this.repository.save(product);

        return product.getProductId();
    }
}
