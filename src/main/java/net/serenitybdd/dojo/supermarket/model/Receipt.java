package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

public class Receipt {

    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
}
