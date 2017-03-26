package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    private List<Product> products = new ArrayList<>();
    private Map<Product, Promotion> deals = new HashMap<>();

    public void addFixedDiscount(Product product, double discount) {
        double percentage = discount/product.getPrice();
        deals.put(product, new Promotion(0, percentage));
    }

    public void addPercentageDiscount(Product product, double percentage) {
        deals.put(product, new Promotion(0, percentage));
    }

    public void addDeal(Product product, Promotion deal) {
        deals.put(product, deal);
    }

    boolean hasDealFor(Product product) {
        return deals.containsKey(product);
    }

    int quantityRequiredForDiscount(Product product) {
        return deals.get(product).getMinQuantity();
    }

    double discount(Product product) {
        return deals.get(product).getDiscount();
    }
}
