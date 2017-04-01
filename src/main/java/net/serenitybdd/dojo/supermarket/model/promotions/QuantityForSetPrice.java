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
    public double applyDiscount(double price, int quantityOfProductSoldSoFar) {
        if (quantityOfProductSoldSoFar == quantityRequiredToBuy) {
            return (-((quantityOfProductSoldSoFar-1)*price) +  setPrice);
        }
        return price;
    }
}
