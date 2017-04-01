package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class DiscountForQuantity extends Promotion {

    private int mustBuyMoreThan;
    private double percentageDiscount;

    public DiscountForQuantity(int mustBuyMoreThan, double percentageDiscount) {
        this.mustBuyMoreThan = mustBuyMoreThan;
        this.percentageDiscount = percentageDiscount;
    }

    public double applyDiscount(double price, int quantityOfProduct) {

        if (quantityOfProduct > mustBuyMoreThan) {
            double discount = quantityOfProduct * price * percentageDiscount;
            return price - discount;
        }
        return price;
    }
}
