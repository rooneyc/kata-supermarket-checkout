package net.serenitybdd.dojo.supermarket.model.promotion;

import net.serenitybdd.dojo.supermarket.model.Promotion;
import org.joda.money.Money;

import java.math.RoundingMode;


public class BuyMoreThanToGetPercentageDiscount implements Promotion {

    private int quantityMustBuyMoreThan;
    private double discount;

    public BuyMoreThanToGetPercentageDiscount(int quantityMustBuyMoreThan, double discount) {
        this.quantityMustBuyMoreThan = quantityMustBuyMoreThan;
        this.discount = discount;
    }

    @Override
    public Money calculateDiscount(int quantityScanned, Money price) {
        if (quantityScanned > quantityMustBuyMoreThan) {
            return price.multipliedBy(quantityScanned).multipliedBy(discount, RoundingMode.CEILING).negated();
        }
        return Money.parse("EUR 0.00");
    }
}
