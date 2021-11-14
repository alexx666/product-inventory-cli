package com.alexx666.products.infra;

import com.alexx666.core.Hashing;

import com.alexx666.products.models.*;

import java.util.*;

public class InMemoryProductDatabase implements ProductRepository, ProductsDAO {

    private final Map<String, Product> products;
    private final Map<String, Map<String, Integer>> userRatings;
    private final Map<String, Integer> inventory;

    private InMemoryProductDatabase(Builder builder) {
        this.products = builder.products;
        this.userRatings = builder.userRatings;
        this.inventory = builder.inventory;
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

        if (isNew) {
            this.inventory.put(productId, 0);
            this.userRatings.put(productId, new HashMap<>());
        }

        return productId;
    }

    @Override
    public void saveUserRating(UserRating userRating) {
        Map<String, Integer> productRatings = this.userRatings.get(userRating.getProductId());
        productRatings.put(userRating.getUserId(), userRating.getRating().getValue());
    }

    @Override
    public ProductDisplay findById(String productId) throws Exception {
        Product product = this.find(productId);

        boolean outOfStock = this.inventory.get(productId) == null || this.inventory.get(productId) == 0;
        double rating = calculateRatingForProduct(productId);
        int totalRatings = this.userRatings.get(productId).size();

        ProductDisplay productDisplay = new ProductDisplay.Builder()
                .identifier(product.getProductId())
                .name(product.getProductName())
                .description(product.getDescription())
                .rating(rating)
                .outOfStock(outOfStock)
                .totalRatings(totalRatings)
                .price(product.getPrice()).build();

        return productDisplay;
    }

    // TODO: implement
    @Override
    public Collection<ProductDisplay> findByName(String name) {
        return null;
    }

    // TODO: implement
    @Override
    public Collection<ProductInventory> findOutOfStockProducts() {
        return null;
    }

    // TODO: implement
    @Override
    public Collection<ProductDisplay> findRelatedProducts(String productId) {
        return null;
    }

    private double calculateRatingForProduct(String productId) {
        Map<String, Integer> productRatings = this.userRatings.get(productId);

        if (productRatings.size() == 0) {
            return 0;
        }

        int totalRating = 0;

        for (Integer rating: productRatings.values()) {
            totalRating += rating;
        }

        return (double) Math.round(totalRating / productRatings.size() * 10) / 10;
    }

    public static class Builder {
        private Map<String, Product> products;
        private Map<String, Map<String, Integer>> userRatings;
        private Map<String, Integer> inventory;

        public Builder() {
            this.products = new HashMap<>();
            this.userRatings = new HashMap<>();
            this.inventory = new HashMap<>();
        }

        public Builder initialProducts(Map<String, Product> products) {
            this.products = products;
            return this;
        }

        public Builder initialInventory(Map<String, Integer> inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder initialRatings(Map<String, Map<String, Integer>> userRatings) {
            this.userRatings = userRatings;
            return this;
        }

        public InMemoryProductDatabase build() {
            return new InMemoryProductDatabase(this);
        }
    }
}
