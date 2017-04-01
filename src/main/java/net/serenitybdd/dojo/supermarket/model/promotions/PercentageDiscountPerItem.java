package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class PercentageDiscountPerItem implements Promotion{

    private final double percentageDiscountInCent;

    public PercentageDiscountPerItem(double percentageDiscountInCent) {
        this.percentageDiscountInCent = percentageDiscountInCent;
    }

    @Override
    public double calculateDiscount(double price, int quantityOfProductAddedSoFar) {
        return price * percentageDiscountInCent;
    }
}
