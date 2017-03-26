package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }
}
