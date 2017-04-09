package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

public class FixedPriceDiscountPerItem implements Promotion {

    private Money discount;

    public FixedPriceDiscountPerItem(Money discount) {
        this.discount = discount;
    }

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        return discount.negated();
    }
}
