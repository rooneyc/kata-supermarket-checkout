package net.serenitybdd.dojo.supermarket.model;

public class Promotion {

    private int minQuantity;

    private double discountPercentage;

    public Promotion(int minQuantity, double discountPercentage) {
        this.minQuantity = minQuantity;
        this.discountPercentage = discountPercentage;
    }

    int getMinQuantity() {
        return minQuantity;
    }

    double getDiscount() {
        return discountPercentage;
    }

}
