package com.alexx666.invcli.products.presenters;

import com.alexx666.core.cleanarch.View;
import com.alexx666.products.models.ProductDisplay;

import java.util.Collection;

public class ProductDisplayConsoleView implements View<ProductDisplay> {
    @Override
    public void present(ProductDisplay object) {
        System.out.println("    ID: " + object.getId());
        System.out.println("    Name: " + object.getName());
        System.out.println("    Description: " + object.getDescription());
        System.out.println("    Rating " + object.getUserRating() + " out of " + object.totalRatings() + " user(s)");
        System.out.println("    Price: " + object.getUnitPrice() + "$ per unit " + (object.isOutOfStock() ? "(out of stock)" : "(in stock)"));
    }

    @Override
    public void present(Collection<ProductDisplay> objects) {
        for (ProductDisplay object: objects) {
            System.out.println(object.getId()
                    + "    |    " + object.getName()
                    + "    |    " + object.getDescription()
                    + "    |    " + object.getUserRating() + " out of " + object.totalRatings() + " user(s)"
                    + "    |    " + object.getUnitPrice() + "$ per unit " + (object.isOutOfStock() ? "(out of stock)" : "(in stock)")
            );
        }
    }
}
