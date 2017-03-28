package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class BuyAndGetSomeFree extends Promotion{

    private int buy;
    private int getFree;

    public BuyAndGetSomeFree(int buy, int getFree) {
        this.buy = buy;
        this.getFree = getFree;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {

        if (quantityOfProduct >= buy) {
            double discount = quantityOfProduct * price * discountPercentage;
            return price - discount;
        }
        return price;
    }
}
