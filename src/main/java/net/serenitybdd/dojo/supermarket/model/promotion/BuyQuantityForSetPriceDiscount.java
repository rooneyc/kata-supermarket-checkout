package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

public class BuyQuantityForSetPriceDiscount implements Promotion {

    private int quantityMustBuy;
    private Money setPrice;

    public BuyQuantityForSetPriceDiscount(int quantityMustBuy, Money setPrice) {
        this.quantityMustBuy = quantityMustBuy;
        this.setPrice = setPrice;
    }

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        if (quantityScanned == quantityMustBuy) {
            return setPrice.minus(price.multipliedBy(quantityScanned));
        }
        return Money.parse("EUR 0.00");
    }

    @Override
    public String description() {
        return quantityMustBuy + " For " + setPrice;
    }

}
