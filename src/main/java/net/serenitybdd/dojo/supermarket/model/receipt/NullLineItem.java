package net.serenitybdd.dojo.supermarket.model.receipt;

public class NullLineItem implements LineItem {

    @Override
    public void incrementQuantity() {
    }

    @Override
    public int getQuantity() {
        return 0;
    }

}
