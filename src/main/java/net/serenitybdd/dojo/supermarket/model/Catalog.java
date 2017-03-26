package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    private List<Product> products = new ArrayList<>();
    private Map<Product, Double> fixedDiscount = new HashMap<>();
    private Map<Product, Double> percentageDiscount = new HashMap<>();
    private Map<Product, Promotion> deals = new HashMap<>();

    public void addFixedDiscount(Product product, double discount) {
        fixedDiscount.put(product, discount);
    }

    boolean hasFixedDiscountFor(Product product) {
        return fixedDiscount.containsKey(product);
    }

    double getFixedDiscountFor(Product product) {
        return fixedDiscount.get(product);
    }

    public void addPercentageDiscount(Product product, double percentage) {
        percentageDiscount.put(product, percentage);
    }

    boolean hasPercentageDiscountFor(Product product) {
        return percentageDiscount.containsKey(product);
    }


    double getPercentageDiscount(Product product) {
        return percentageDiscount.get(product);
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
