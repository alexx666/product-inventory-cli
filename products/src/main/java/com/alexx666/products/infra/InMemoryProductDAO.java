package com.alexx666.products.infra;

import com.alexx666.products.models.Product;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductInventory;
import com.alexx666.products.data.ProductsDAO;

import java.util.*;

public class InMemoryProductDAO implements ProductsDAO {

    private final Map<String, Product> products;
    private final Map<String, Map<String, Integer>> userRatings;

    private InMemoryProductDAO(Builder builder) {
        this.products = builder.products;
        this.userRatings = builder.userRatings;
    }

    @Override
    public ProductDisplay findById(String productId) throws Exception {
        Product product = this.products.get(productId);

        if (product == null) {
            throw new Exception("Product (" + productId + ") not found!");
        }

        return from(product, this.userRatings.get(productId));
    }

    @Override
    public Collection<ProductDisplay> findByName(String name) {
        Collection<ProductDisplay> matchingProducts = new ArrayList<>();

        for (Product product: this.products.values()) {
            if (!product.getProductName().contains(name)) {
                continue;
            }

            matchingProducts.add(from(product, userRatings.get(product.getProductId())));
        }

        return  matchingProducts;
    }

    @Override
    public Collection<ProductInventory> findOutOfStockProducts() {
        Collection<ProductInventory> outOfStockItems = new ArrayList<>();

        for (Product product: this.products.values()) {
            if (product.getItemsInStock() > 0) {
                continue;
            }

            ProductInventory productInventory = new ProductInventory(
                    product.getProductId(),
                    product.getProductName(),
                    product.getItemsInStock());

            outOfStockItems.add(productInventory);
        }

        return outOfStockItems;
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

        double totalRating = 0;

        for (Integer rating: productRatings.values()) {
            totalRating += rating;
        }

        return (double) Math.round(totalRating / productRatings.size() * 10) / 10;
    }

    public static final class Builder {
        private Map<String, Product> products;
        private Map<String, Map<String, Integer>> userRatings;

        /**
         * {@link InMemoryProductDAO} builder
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

        public InMemoryProductDAO build() {
            return new InMemoryProductDAO(this);
        }
    }

    private ProductDisplay from(Product product, Map<String, Integer> ratings) {
        ProductDisplay.Builder productDisplayBuilder = new ProductDisplay.Builder()
                .identifier(product.getProductId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .outOfStock(product.getItemsInStock() == 0);

        if (ratings == null || ratings.isEmpty()) {
            return productDisplayBuilder.build();
        }

        String productId = product.getProductId();
        double rating = calculateRatingForProduct(productId);
        int totalRatings = ratings.size();

        return productDisplayBuilder
                .rating(rating)
                .totalRatings(totalRatings)
                .build();
    }
}
