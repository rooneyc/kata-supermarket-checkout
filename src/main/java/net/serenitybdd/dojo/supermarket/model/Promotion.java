package net.serenitybdd.dojo.supermarket.model;

import org.joda.money.Money;

public interface Promotion {

    Money calculateDiscount(int quantityScanned, Money price);

    String description();

}
