package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private Collection<Line> lineItems = new ArrayList<>();
    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Collection<Line> itemsPurchased() {
        return lineItems;
    }

    public void add(Product product) {
        lineItems.add(new Line (product.getDescription(), product.getPrice()));
        totalPrice = totalPrice.plus(product.getPrice());
    }
}
