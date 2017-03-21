package net.serenitybdd.dojo.supermarket.model;

import java.util.List;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        List<Product> products = theCart.getItems();
        double price = 0.00;
        for (Product product : products) {
            price = price + catalog.getProductPrice(product);
        }
        return new Receipt(price);
    }
}
