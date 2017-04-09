package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.catalog.Code;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {

        Receipt receipt = new Receipt();

        for (Article item : theCart.getItems()) {
            Code code = scan(item);
            Product product = catalog.getProduct(code);
            receipt.add(product);
            Promotion promotion = catalog.getPromotion(code);
            receipt.applyPromotion(promotion, product);
        }

        return receipt;
    }

    private Code scan(Article item) {
        return new Code(item.getCode());
    }
}
