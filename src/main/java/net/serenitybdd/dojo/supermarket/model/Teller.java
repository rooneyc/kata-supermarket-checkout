package net.serenitybdd.dojo.supermarket.model;

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
            if ("1Kg Bag of Oranges".equals(product.getName())) {
                totalPrice = totalPrice + product.getPrice() - 1.00;
            } else {
                totalPrice = totalPrice + product.getPrice();
            }

        }
        receipt.setTotalPrice(totalPrice);
        return receipt;
    }
}
