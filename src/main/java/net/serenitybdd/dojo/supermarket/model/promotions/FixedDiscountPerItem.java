package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class FixedDiscountPerItem implements Promotion {

    private final int discountDiscountInCent;

    public FixedDiscountPerItem(int discountDiscountInCent) {
        this.discountDiscountInCent = discountDiscountInCent;
    }

    @Override
    public double calculateDiscount(double price, int quantityOfProductAddedSoFar) {
       return discountDiscountInCent;
    }

}
