package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addFixedDiscount(Product product, int fixedDiscount) {
        double percentage = (double)fixedDiscount/product.getPrice();
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
        return deals.get(product).getMustBuyMoreThan();
    }

    double discount(Product product) {
        return deals.get(product).getDiscount();
    }

    public void addBuyGetFreeDeal(Product product, int buy, int getFree) {
        double percentage = (double)getFree/(double)buy;
        int mustBuyMoreThan = buy - 1;
        deals.put(product, new Promotion(mustBuyMoreThan, percentage));
    }
}
