package com.alexx666.products.infra;

import com.alexx666.core.Hashing;

import com.alexx666.products.models.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository, ProductsDAO {

    private Map<String, Product> products;
    private Map<String, Integer> userRatings;

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

        // TODO: use in mapper after figuring out a data structure for in memory
        Product productWithId = new Product.Builder()
                .identifier(productId)
                .withName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        return productWithId;
    }

    @Override
    public String saveProduct(Product product) {
        boolean isNew = product.getProductId() == null;

        String productId = isNew ? Hashing.getRandomHash() : product.getProductId();

        this.products.put(productId, product);

        return productId;
    }

    @Override
    public void saveUserRating(UserRating userRating) {
        String ratingKey = userRating.getUserId() + ":" + userRating.getProductId();
        this.userRatings.put(ratingKey, userRating.getRating().getValue());
    }

    @Override
    public ProductDisplay findById(String productId) throws Exception {
        Product product = this.find(productId);

        // TODO: get all user ratings for product and calculate total rating

        // TODO: use in mapper after figuring out a data structure for in memory
        ProductDisplay productDisplay = new ProductDisplay.Builder()
                .identifier(product.getProductId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice()).build();

        return productDisplay;
    }

    @Override
    public Collection<ProductDisplay> findByName(String name) {
        return null;
    }

    @Override
    public Collection<ProductInventory> findOutOfStockProducts() {
        return null;
    }

    @Override
    public Collection<ProductDisplay> findRelatedProducts(String productId) {
        return null;
    }

    public static class Builder {
        private Map<String, Product> products;
        private Map<String, Integer> userRatings;

        public Builder() {
            this.products = new HashMap<>();
            this.userRatings = new HashMap<>();
        }

        public Builder initialInventory(Map<String, Product> products) {
            this.products = products;
            return this;
        }

        public Builder initialRatings(Map<String, Integer> userRatings) {
            this.userRatings = userRatings;
            return this;
        }

        public InMemoryProductRepository build() {
            return new InMemoryProductRepository(this);
        }
    }
}
