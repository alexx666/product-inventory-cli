package com.alexx666.products;

import com.alexx666.products.services.AddNewProduct;
import com.alexx666.products.domain.Product;
import com.alexx666.products.services.RateProduct;
import com.alexx666.products.domain.ProductRepository;

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
