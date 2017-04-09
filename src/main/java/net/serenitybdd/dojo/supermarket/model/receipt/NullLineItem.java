package net.serenitybdd.dojo.supermarket.model.receipt;

public class NullLineItem implements CanGetAndIncrementQuantity {

    @Override
    public void incrementQuantity() {
    }

    @Override
    public int getQuantity() {
        return 0;
    }

}
