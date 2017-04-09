package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

public class NullPromotion implements Promotion {

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        return Money.parse("EUR 0.00");
    }

    @Override
    public String description() {
        return null;
    }
}
