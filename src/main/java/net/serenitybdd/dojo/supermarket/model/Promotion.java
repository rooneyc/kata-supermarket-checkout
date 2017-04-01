package net.serenitybdd.dojo.supermarket.model;

public interface Promotion {

    double calculateDiscount(double price, int quantityOfProductAddedSoFar);

}
