package com.alexx666.products.infra;

import com.alexx666.core.Hashing;

import com.alexx666.products.models.*;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> products;
    private final Map<String, Map<String, Integer>> userRatings;

    private InMemoryProductRepository(Builder builder) {
        this.products = builder.products;
        this.userRatings = builder.userRatings;
    }

    @Override
    public Product find(String productId) throws Exception {
        Product product = this.products.get(productId);

        if (product == null) {
            throw new Exception("Product (" + productId + ") not found!");
        }

        return product;
    }

    @Override
    public String saveProduct(Product product) {
        boolean isNew = product.getProductId() == null;

        String productId = isNew ? Hashing.getRandomHash() : product.getProductId();

        product.setProductId(productId);

        this.products.put(productId, product);

        if (isNew) {
            this.userRatings.put(productId, new HashMap<>());
        }

        return productId;
    }

    @Override
    public void saveUserRating(UserRating userRating) {
        Map<String, Integer> productRatings = this.userRatings.get(userRating.getProductId());
        productRatings.put(userRating.getUserId(), userRating.getRating().getValue());
    }

    public static class Builder {
        private Map<String, Product> products;
        private Map<String, Map<String, Integer>> userRatings;

        public Builder() {
            this.products = new HashMap<>();
            this.userRatings = new HashMap<>();
        }

        public Builder initialProducts(Map<String, Product> products) {
            this.products = products;
            return this;
        }

        public Builder initialRatings(Map<String, Map<String, Integer>> userRatings) {
            this.userRatings = userRatings;
            return this;
        }

        public InMemoryProductRepository build() {
            return new InMemoryProductRepository(this);
        }
    }
}
