package net.serenitybdd.dojo.supermarket.model;

import java.util.Collection;

public class Receipt {

    private Collection<Product> purchasedItems;

    private double totalPrice;

    Receipt(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Collection<Product> getPurchasedItems() {
        return purchasedItems;
    }
}
