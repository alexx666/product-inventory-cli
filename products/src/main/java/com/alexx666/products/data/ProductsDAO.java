package com.alexx666.products.data;

import com.alexx666.products.models.Product;
import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductInventory;

import java.util.Collection;

/**
 * Interface for querying product information.
 * When implemented should aggregate relevant product information
 * from one to multiple sources and map them into {@link ProductDisplay} or {@link ProductInventory}
 */
public interface ProductsDAO {
    /**
     * Function for searching specific {@link ProductDisplay}
     * objects given their unique object identifier (UUID)
     *
     * @param productId String value of the ID
     * @return a {@link ProductDisplay} object
     * @throws Exception in case the object does not exist or other reasons related to the query
     */
    ProductDisplay findById(String productId) throws Exception;

    /**
     * Searches the database for {@link Product} matching the name provided,
     * aggregates additional information and maps it to {@link Collection<ProductDisplay>}
     *
     * @param name {@link String} of characters to search for in the name
     * @return {@link Collection<ProductDisplay>} containing matching products
     */
    Collection<ProductDisplay> findByName(String name);

    /**
     * Presents a simplified version of the product information
     * filtering only those {@link Product} that are out of stock
     *
     * @return {@link Collection<ProductInventory>} for all {@link Product} that are out of stock
     */
    Collection<ProductInventory> findOutOfStockProducts();

    /**
     * Finds all related products to a specified {@link Product} ID
     *
     * @param productId a {@link String} representing a unique object identifier (UUID) for the product
     * @return {@link Collection<ProductDisplay>} items that are related to the {@link Product}
     */
    Collection<ProductDisplay> findRelatedProducts(String productId);
}
