package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Receipt {

    private Collection<Product> purchasedItems = new ArrayList<>();

    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice/100;
    }

    public Collection<Product> getPurchasedItems() {
        return purchasedItems;
    }

    public int numberOfItemsSold() {
        return purchasedItems.size();
    }

    void addItem(Product product) {
        purchasedItems.add(product);
        totalPrice += product.getPrice();
    }

    int quantityOfProductAdded(Product product) {
        return Collections.frequency(purchasedItems, product);
    }

    void applyDiscount(double discount) {
        totalPrice -= discount;
    }
}
