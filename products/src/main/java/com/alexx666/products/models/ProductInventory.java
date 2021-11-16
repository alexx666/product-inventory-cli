package com.alexx666.products.models;

// DTO object
public class ProductInventory {
    private final String productId;
    private final String productName;
    private final int currentStock;

    public ProductInventory(String productId, String productName, int currentStock) {
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }
}
