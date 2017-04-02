package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

public class Receipt {
    public Money getTotalPrice() {
        return Money.parse("USD 0.00");
    }
}
