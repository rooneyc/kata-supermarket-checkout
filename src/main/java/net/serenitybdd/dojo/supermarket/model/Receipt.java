package net.serenitybdd.dojo.supermarket.model;

public class Receipt {

    private double totalPrice;

    public Receipt(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
