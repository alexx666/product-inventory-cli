package com.alexx666.products;

import com.alexx666.products.models.ProductDisplay;
import com.alexx666.products.models.ProductInventory;
import com.alexx666.products.models.ProductsDAO;

import java.util.Collection;

public class ProductsQueryHandler implements ProductsDAO {

    private ProductsDAO productsDAO;

    public ProductsQueryHandler(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Override
    public ProductDisplay findById(String productId) throws Exception {
        return this.productsDAO.findById(productId);
    }

    @Override
    public Collection<ProductDisplay> findByName(String name) {
        return this.productsDAO.findByName(name);
    }

    @Override
    public Collection<ProductInventory> findOutOfStockProducts() {
        return this.productsDAO.findOutOfStockProducts();
    }

    @Override
    public Collection<ProductDisplay> findRelatedProducts(String productId) {
        return this.productsDAO.findRelatedProducts(productId);
    }
}
