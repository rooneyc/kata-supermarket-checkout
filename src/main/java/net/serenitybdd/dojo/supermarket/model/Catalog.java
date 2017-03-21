package net.serenitybdd.dojo.supermarket.model;

public class Catalog {

    double productPrice = 0.00;

    public double getProductPrice(Product milk) {
        return productPrice;
    };

    public void setProductPrice(Product milk, double v) {
        this.productPrice = v;
    }

}
