package com.alexx666.products.commands;

import com.alexx666.core.cqrs.Command;

// In typescript these can be simple interfaces
public class RateProduct extends Command {

    private final String productId;
    private final String userId;
    private final int rating;

    public RateProduct(String productId, String userId, int rating) {
        super();

        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
    }

    public String getProductId() { return this.productId; }
    public String getUserId() { return this.userId; }
    public int getRating() { return this.rating; }
}
