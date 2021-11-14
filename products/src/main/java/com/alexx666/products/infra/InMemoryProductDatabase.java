package com.alexx666.products.infra;

import com.alexx666.core.Hashing;

import com.alexx666.products.models.*;

import java.util.*;

public class InMemoryProductDatabase implements ProductRepository, ProductsDAO {

    private final Map<String, Product> products;
    private final Map<String, Map<String, Integer>> userRatings;

    private InMemoryProductDatabase(Builder builder) {
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

    @Override
    public ProductDisplay findById(String productId) throws Exception {
        Product product = this.find(productId);

        System.out.println("Items in stock: " + product.getItemsInStock());

        boolean outOfStock = product.getItemsInStock() == 0;
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

        public InMemoryProductDatabase build() {
            return new InMemoryProductDatabase(this);
        }
    }
}
