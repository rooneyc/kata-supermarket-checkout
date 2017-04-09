package net.serenitybdd.dojo.supermarket.model;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {

        Receipt receipt = new Receipt();

        for (Article item : theCart.getItems()) {
            Product product = catalog.getProductFromCode(item.getCode());
            receipt.add(product);
            Promotion promotion = catalog.getPromotionFromCode(item.getCode());
            receipt.applyPromotionToProduct(promotion, product);
        }

        return receipt;
    }
}
