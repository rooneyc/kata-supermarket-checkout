package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private Collection<LineItem> lineItems = new ArrayList<>();
    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<LineItem> itemsPurchased() {
        return lineItems;
    }
}
