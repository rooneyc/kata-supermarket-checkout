package net.serenitybdd.dojo.supermarket.model;

import java.util.Collections;
import java.util.List;

public class Teller {

    private final Catalog catalog;

    public Teller(Catalog catalog) {
        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<Product> products = theCart.getItems();
        double totalPrice = 0;
        for (Product product : products) {
            receipt.addItem(product);
            int quantityOfProductPurchasedSoFar = Collections.frequency(receipt.getPurchasedItems(), product);
            totalPrice = totalPrice + priceAfterPromotion(product, quantityOfProductPurchasedSoFar);
        }
        receipt.setTotalPrice(totalPrice / 100);
        return receipt;
    }

    private double priceAfterPromotion(Product product, int quantityOfProduct) {
        double price = product.getPrice();
        if (catalog.hasDealFor(product)) {
            if (quantityOfProduct > catalog.quantityRequiredForDiscount(product)) {
                double discount = quantityOfProduct * product.getPrice() * catalog.discountFor(product);
                return price - discount;
            }
        }
        return price;
    }
}
