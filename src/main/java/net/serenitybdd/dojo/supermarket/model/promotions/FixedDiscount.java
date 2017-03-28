package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class FixedDiscount extends Promotion {

    private int discount = 0;

    public FixedDiscount(int fixedDiscount) {
        this.discount = fixedDiscount;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {
       return price - discount;
    }

}
