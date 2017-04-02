package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

import java.math.RoundingMode;

public class PercentageDiscountPerItem implements Promotion {

    private double discount;

    public PercentageDiscountPerItem(double discount) {
        this.discount = discount;
    }

    @Override
    public Money calculateDiscount(Money price) {
        return price.multipliedBy(discount, RoundingMode.CEILING);
    }
}
