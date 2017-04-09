package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

public class BuyToGetFree implements Promotion {

    private int quantityMustBuyMoreThan;
    private int getFree;

    public BuyToGetFree(int quantityMustBuy, int getFree) {
        this.quantityMustBuyMoreThan = quantityMustBuy+1;
        this.getFree = getFree;
    }

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        if (quantityScanned % quantityMustBuyMoreThan == 0) {
            return price.multipliedBy(getFree).negated();
        }
        return Money.parse("EUR 0.00");
    }
}
