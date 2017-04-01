package net.serenitybdd.dojo.supermarket.model.promotions;

import net.serenitybdd.dojo.supermarket.model.Promotion;

public class QuantityForSetPrice implements Promotion {

    private final int quantityRequiredToBuy;
    private final int setPrice;

    public QuantityForSetPrice(int quantityRequiredToBuy, int setPrice) {
        this.quantityRequiredToBuy = quantityRequiredToBuy;
        this.setPrice = setPrice;
    }

    @Override
    public double calculateDiscount(double price, int quantityOfProductAddedSoFar) {

        if (quantityOfProductAddedSoFar == quantityRequiredToBuy) {
            return (quantityOfProductAddedSoFar*price) - setPrice;
        }
        return 0.00;
    }
}
