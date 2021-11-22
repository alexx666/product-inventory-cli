package com.alexx666.products.infra.mem;

import com.alexx666.core.cqrs.Hashing;

import com.alexx666.products.data.ProductRepository;
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
        boolean exists = this.products.containsKey(productId);

        if (!exists) {
            throw new Exception("Product (" + productId + ") not found!");
        }

        return this.products.get(productId);
    }

    @Override
    public String saveProduct(Product product) {
        String productId = product.isNew() ? Hashing.getRandomHash() : product.getProductId();

        product.setProductId(productId);

        this.products.put(productId, product);

        return productId;
    }

    @Override
    public void saveUserRating(UserRating userRating) {
        String productId = userRating.getProductId();

        boolean hasNotBeenRated = !this.userRatings.containsKey(productId);

        if (hasNotBeenRated) {
            this.userRatings.put(productId, new HashMap<>());
        }

        Map<String, Integer> productRatings = this.userRatings.get(productId);
        productRatings.put(userRating.getUserId(), userRating.getRating().getValue());
    }

    public static final class Builder {
        private Map<String, Product> products;
        private Map<String, Map<String, Integer>> userRatings;

        /**
         * {@link InMemoryProductRepository} builder
         */
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
