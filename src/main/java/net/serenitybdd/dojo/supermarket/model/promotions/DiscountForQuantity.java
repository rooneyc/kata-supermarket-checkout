package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class DiscountForQuantity implements Promotion {

    private final int mustBuyMoreThan;
    private final double percentageDiscount;

    public DiscountForQuantity(int mustBuyMoreThan, double percentageDiscount) {
        this.mustBuyMoreThan = mustBuyMoreThan;
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {

        if (quantityOfProduct > mustBuyMoreThan) {
            double discount = quantityOfProduct * price * percentageDiscount;
            return price - discount;
        }
        return price;
    }
}
