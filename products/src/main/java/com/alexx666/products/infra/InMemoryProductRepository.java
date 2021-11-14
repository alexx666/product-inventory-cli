package com.alexx666.products.infra;

import com.alexx666.core.Hashing;
import com.alexx666.products.models.Product;
import com.alexx666.products.models.ProductRepository;

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
    public String save(Product product) {
        boolean isNew = product.getProductId() == null;

        String productId = isNew ? Hashing.getRandomHash() : product.getProductId();

        Product productWithId = new Product.Builder()
                .identifier(productId)
                .withName(product.getProductName())
                .withRatings(product.getRatings())
                .build();

        this.products.put(productId, productWithId);

        return productId;
    }
}
