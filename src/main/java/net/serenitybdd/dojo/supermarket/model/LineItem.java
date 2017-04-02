package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

public class LineItem {

    private String description;
    private Money price;

    public LineItem(String description, Money price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
