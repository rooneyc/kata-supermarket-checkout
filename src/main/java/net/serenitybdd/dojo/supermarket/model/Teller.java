package net.serenitybdd.dojo.supermarket.model;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        return new Receipt();
    }
}
