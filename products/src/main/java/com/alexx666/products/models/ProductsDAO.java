package com.alexx666.products.models;

import java.util.Collection;

public interface ProductsDAO {
    ProductDisplay findById(String productId) throws Exception;
    Collection<ProductDisplay> findByName(String name);
    Collection<ProductInventory> findOutOfStockProducts();
    Collection<ProductDisplay> findRelatedProducts(String productId);
}
