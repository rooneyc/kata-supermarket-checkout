package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

public class BuyToGetFree implements Promotion {

    private int quantityMustBuy;
    private int getFree;

    public BuyToGetFree(int quantityMustBuy, int getFree) {
        this.quantityMustBuy = quantityMustBuy;
        this.getFree = getFree;
    }

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        if (quantityScanned > quantityMustBuy) {
            return price.multipliedBy(getFree).negated();
        }
        return Money.parse("EUR 0.00");
    }

}
