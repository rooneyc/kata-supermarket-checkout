package net.serenitybdd.dojo.supermarket.model;

public class Promotion {

    private int mustBuyMoreThan;

    private double discountPercentage;

    public Promotion(int mustBuyMoreThan, double discountPercentage) {
        this.mustBuyMoreThan = mustBuyMoreThan;
        this.discountPercentage = discountPercentage;
    }

    int getMustBuyMoreThan() {
        return mustBuyMoreThan;
    }

    double getDiscount() {
        return discountPercentage;
    }

}
