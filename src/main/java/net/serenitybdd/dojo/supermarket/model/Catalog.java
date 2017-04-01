package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addPromotionForProduct(Product product, Promotion promotion) {
        deals.put(product, promotion);
    }

    double priceAfterPromotion(Product product, int quantityOfProduct) {

        double price = product.getPrice();

        if (this.hasDealFor(product)) {
            Promotion promotion = deals.get(product);
            return promotion.applyDiscount(price, quantityOfProduct);
        }
        return price;
    }

    private boolean hasDealFor(Product product) {
        return deals.containsKey(product);
    }

}
