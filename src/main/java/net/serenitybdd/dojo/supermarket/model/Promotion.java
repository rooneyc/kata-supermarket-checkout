package net.serenitybdd.dojo.supermarket.model;

public class Promotion {

    private int mustBuyMoreThan = 0;

    private double discountPercentage = 0.00;

    public Promotion(int mustBuyMoreThan, double discountPercentage) {
        this.mustBuyMoreThan = mustBuyMoreThan;
        this.discountPercentage = discountPercentage;
    }

    protected Promotion() {
    }

    int getMustBuyMoreThan() {
        return mustBuyMoreThan;
    }

    double getDiscount() {
        return discountPercentage;
    }

    public double applyDiscount(double price, int quantityOfProduct) {

        if (quantityOfProduct > mustBuyMoreThan) {
            double discount = quantityOfProduct * price * discountPercentage;
            return price - discount;
        }
        return price;
    }

}
