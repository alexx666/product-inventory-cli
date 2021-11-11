package com.alexx666.products.domain;

import java.util.Collection;

public interface ProductsDAO {
    ProductDisplay findById(String productId);
    Collection<ProductDisplay> findByName(String name);
    Collection<ProductInventory> findOutOfStockProducts();
    Collection<ProductDisplay> findRelatedProducts(String productId);
}
