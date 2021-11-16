package com.alexx666.invcli.products.presenters;

import com.alexx666.core.cleanarch.View;
import com.alexx666.products.models.ProductInventory;

import java.util.Collection;

public class ProductInventoryConsoleView implements View<ProductInventory> {
    @Override
    public void present(ProductInventory object) {
        System.out.println("    ID: " + object.getProductId());
        System.out.println("    Name: " + object.getProductName());
        System.out.println("    Current Stock: " + object.getCurrentStock());
    }

    @Override
    public void present(Collection<ProductInventory> objects) {
        for (ProductInventory object: objects) {
            System.out.println(object.getProductId()
                    + "    |    " + object.getProductName()
                    + "    |    " + object.getCurrentStock() + " items"
            );
        }
    }
}
