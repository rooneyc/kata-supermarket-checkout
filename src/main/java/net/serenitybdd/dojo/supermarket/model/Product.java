package net.serenitybdd.dojo.supermarket.model;

public class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    int getPrice() {
        return price;
    }

 }
