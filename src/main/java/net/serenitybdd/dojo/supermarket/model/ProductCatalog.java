package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private Map<String, Product> products = new HashMap<>();

    Product getFromCode(String code) {
        return products.get(code);
    }

    public void add(String code, Product product) {
        products.put(code, product);
    }
}
