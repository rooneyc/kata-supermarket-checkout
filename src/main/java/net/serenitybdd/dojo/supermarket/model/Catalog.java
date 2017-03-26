package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    private List<Product> products = new ArrayList<>();
    private Map<Product, Double> fixedDiscount = new HashMap<>();

    public void addFixedDiscount(Product product, double discount) {
        fixedDiscount.put(product, discount);
    }

    boolean hasFixedDiscountFor(Product product) {
        return fixedDiscount.containsKey(product);
    }

    double getFixedDiscountFor(Product product) {
        return fixedDiscount.get(product);
    }
}
