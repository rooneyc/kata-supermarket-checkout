package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class BuyAndGetSomeFree implements Promotion {

    private int quantityRequiredToBuy;
    private int quantityGetFree;

    public BuyAndGetSomeFree(int quantityRequiredToBuy, int quantityGetFree) {
        this.quantityRequiredToBuy = quantityRequiredToBuy + quantityGetFree;
        this.quantityGetFree = quantityGetFree;
    }

    @Override
    public double applyDiscount(double price, int quantityOfProductSoldSoFar) {

        if (quantityOfProductSoldSoFar == quantityRequiredToBuy) {
            double discount = price * quantityGetFree;
            return price - discount;
        }
        return price;
    }
}