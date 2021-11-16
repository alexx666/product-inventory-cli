package com.alexx666.products.models;

import java.util.Collection;

public interface ProductsDAO {
    /**
     * Function for searching specific {@link ProductDisplay}
     * objects given their unique object identifier (UUID)
     *
     * @param productId String value of the ID
     * @returns a {@link ProductDisplay} object
     * @throws Exception in case the object does not exist or other reasons related to the query
     */
    ProductDisplay findById(String productId) throws Exception;
    Collection<ProductDisplay> findByName(String name);
    Collection<ProductInventory> findOutOfStockProducts();
    Collection<ProductDisplay> findRelatedProducts(String productId);
}
