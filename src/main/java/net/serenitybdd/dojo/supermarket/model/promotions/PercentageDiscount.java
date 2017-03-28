package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class PercentageDiscount extends Promotion{

    private final double percentageDiscount;

    public PercentageDiscount(double discount) {
        this.percentageDiscount = discount;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {
        double discount = price * percentageDiscount;
        return price - discount;
    }
}
