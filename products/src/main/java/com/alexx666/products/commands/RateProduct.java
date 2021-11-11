package com.alexx666.products.commands;

import com.alexx666.core.Command;

// In typescript these can be simple interfaces
public class RateProduct extends Command {

    private String productId;
    private String userId;
    private int rating;

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
