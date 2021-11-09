package com.alexx666.core.products.Commands.Infrastructure;

import com.alexx666.core.products.Commands.Domain.Product;
import com.alexx666.core.products.Commands.Domain.ProductRepository;
import com.alexx666.core.utils.Hashing;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private Map<String, Product> products;

    public InMemoryProductRepository(Map<String, Product> products) {
        this.products = products;
    }

    public InMemoryProductRepository() {
        this(new HashMap<>());
    }

    @Override
    public Product find(String productId) throws Exception {

        Product product = this.products.get(productId);

        if (product == null) {
            throw new Exception("Error: Product " + productId + " not found!");
        }

        return this.products.get(productId);
    }

    @Override
    public void save(Product product) {
        boolean isNew = product.getProductId() == null;

        String productId = isNew ? Hashing.getRandomHash() : product.getProductId();

        product.setProductId(productId);

        this.products.put(productId, product);
    }
}
