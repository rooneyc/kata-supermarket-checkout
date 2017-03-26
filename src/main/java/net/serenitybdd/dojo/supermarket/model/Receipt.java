package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private Collection<Product> purchasedItems = new ArrayList<>();

    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<Product> getPurchasedItems() {
        return purchasedItems;
    }

    void addItem(Product product) {
        purchasedItems.add(product);
    }
}
