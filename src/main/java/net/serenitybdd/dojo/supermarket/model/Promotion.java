package net.serenitybdd.dojo.supermarket.model;

public class Promotion {

    private int minQuantity;

    private double discount;

    public Promotion(int minQuantity, double discount) {
        this.minQuantity = minQuantity;
        this.discount = discount;
    }

    int getMinQuantity() {
        return minQuantity;
    }

    double getDiscount() {
        return discount;
    }

}
