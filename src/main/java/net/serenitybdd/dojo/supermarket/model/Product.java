package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

public class Product {

    private String description;
    private Money price;

    public Product(String description, Money price) {
        this.description = description;
        this.price = price;
    }

    Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
