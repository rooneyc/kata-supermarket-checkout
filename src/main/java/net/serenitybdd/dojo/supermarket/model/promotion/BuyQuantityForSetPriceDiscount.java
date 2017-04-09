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

//        2 = pricescaneed_to_date + x
//                2 - x = pricescaneed_to_date
//                - x = pricescaneed_to_date - setprice
//        x = setsetprice - pricescaneed_to_date
//        e.g.
//        x = 2 - 1.2*2
//        x = -0.40
    }

}
