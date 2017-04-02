package net.serenitybdd.dojo.supermarket.model;

public class Teller {

    private final ProductCatalog catalog;

    public Teller(ProductCatalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {

        Receipt receipt = new Receipt();

        for (Item item : theCart.getItems()) {
            Product product = catalog.getFromCode(item.getCode());
            receipt.add(product);
        }

        return receipt;
    }
}
