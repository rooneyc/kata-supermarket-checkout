package net.serenitybdd.dojo.supermarket.model;

public class Teller {

    private final ProductCatalog catalog;

    public Teller(ProductCatalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Product product = catalog.getFromCode(theCart.getItems().get(0).getCode());
        Receipt receipt = new Receipt();
        receipt.setTotalPrice(product.getPrice());
        return receipt;
    }
}
