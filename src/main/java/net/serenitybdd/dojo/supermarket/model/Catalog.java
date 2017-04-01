package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addPromotionForProduct(Product product, Promotion promotion) {
        deals.put(product, promotion);
    }

    double calculateDiscount(Product product, int quantityOfProductAddedSoFar) {

        if (this.hasDealFor(product)) {
            Promotion promotion = deals.get(product);
            return promotion.calculateDiscount(product.getPrice(), quantityOfProductAddedSoFar);
        }
        return 0.00;
    }

    private boolean hasDealFor(Product product) {
        return deals.containsKey(product);
    }

}
