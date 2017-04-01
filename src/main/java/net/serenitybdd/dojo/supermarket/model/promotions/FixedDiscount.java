package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class FixedDiscount extends Promotion {

    private int discountDiscountInCent = 0;

    public FixedDiscount(int discountDiscountInCent) {
        this.discountDiscountInCent = discountDiscountInCent;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {
       return price - discountDiscountInCent;
    }

}
