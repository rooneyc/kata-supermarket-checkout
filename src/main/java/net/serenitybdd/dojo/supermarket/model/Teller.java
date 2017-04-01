package net.serenitybdd.dojo.supermarket.model;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {
        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        for (Product product : theCart.getItems()) {
            receipt.addItem(product);
            double discount = catalog.calculateDiscount(product, receipt.quantityOfProductAdded(product));
            receipt.applyDiscount(discount);
        }
        return receipt;
    }

}
