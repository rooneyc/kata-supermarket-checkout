package net.serenitybdd.dojo.supermarket.model.catalog;

import net.serenitybdd.dojo.supermarket.model.Product;
import net.serenitybdd.dojo.supermarket.model.Promotion;

public class Entry {

    private Product product;
    private Promotion promotion;

    public Entry(Product product, Promotion promotion) {
        this.product = product;
        this.promotion = promotion;
    }

    public Product getProduct() {
        return product;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
