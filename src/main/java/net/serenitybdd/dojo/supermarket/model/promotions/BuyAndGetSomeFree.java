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
    public double calculateDiscount(double price, int quantityOfProductAddedSoFar) {

        if (quantityOfProductAddedSoFar == quantityRequiredToBuy) {
            return price * quantityGetFree;
        }
        return 0.00;
    }
}