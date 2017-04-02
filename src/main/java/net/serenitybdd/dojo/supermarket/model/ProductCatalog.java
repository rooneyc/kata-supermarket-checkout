package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private Map<Barcode, Product> products = new HashMap<>();

    Product getFromCode(Barcode barCode) {
        return products.get(barCode);
    }

    public void add(Barcode barCode, Product product) {
        products.put(barCode, product);
    }
}
