package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class BuyAndGetSomeFree implements Promotion {

    private int buy;
    private int getFree;

    public BuyAndGetSomeFree(int buy, int getFree) {
        this.buy = buy + getFree;
        this.getFree = getFree;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProduct) {

        if (quantityOfProduct == buy) {
            double discount = price * getFree;
            return price - discount;
        }
        return price;
    }
}