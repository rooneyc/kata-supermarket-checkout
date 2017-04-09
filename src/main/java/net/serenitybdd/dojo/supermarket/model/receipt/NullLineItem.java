package net.serenitybdd.dojo.supermarket.model.receipt;

public class NullLineItem implements CanIncrementQuantity{

    @Override
    public void incrementQuantity() {
    }

}
