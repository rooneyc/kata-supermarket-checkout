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
        double totalPrice = 0;
        for (Product product : products) {
            receipt.addItem(product);
            totalPrice = totalPrice + product.getPrice();
            if (catalog.hasDealFor(product)) {
                int dealItemsPurchased = Collections.frequency(receipt.getPurchasedItems(), product);
                if (dealItemsPurchased > catalog.quantityRequiredForDiscount(product)) {
                    double discount = dealItemsPurchased * product.getPrice() * catalog.discount(product);
                    totalPrice = totalPrice - discount;
                }
            }
        }
        receipt.setTotalPrice(totalPrice/100);
        return receipt;
    }


}
