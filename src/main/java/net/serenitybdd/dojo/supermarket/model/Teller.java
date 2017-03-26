package net.serenitybdd.dojo.supermarket.model;

import java.util.Collections;
import java.util.List;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {
        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<Product> products = theCart.getItems();
        double totalPrice = 0.00;
        for (Product product : products) {
            receipt.addItem(product);
            if (catalog.hasFixedDiscountFor(product)) {
                totalPrice = totalPrice + product.getPrice() - catalog.getFixedDiscountFor(product);
            } else if (catalog.hasPercentageDiscountFor(product)) {
                totalPrice = totalPrice + (product.getPrice() * (1.00 - catalog.getPercentageDiscount(product)));
            } else if (catalog.hasPercentageDiscountOnQuantityFor(product)) {
                if (Collections.frequency(receipt.getPurchasedItems(), product) == catalog.quantityRequiredForDiscount()) {

                }
            } else {
                totalPrice = totalPrice + product.getPrice();
            }
        }
        receipt.setTotalPrice(totalPrice);
        return receipt;
    }
}
