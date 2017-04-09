package net.serenitybdd.dojo.supermarket.model.receipt;

import net.serenitybdd.dojo.supermarket.model.Product;
import org.joda.money.Money;

public class ProductLineItem implements LineItem {

    private String description;
    private int quantity = 1;
    private Money price;

    public ProductLineItem(Product product) {
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    @Override
    public void incrementQuantity() {
        quantity += 1;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductLineItem lineItem = (ProductLineItem) o;

        if (description != null ? !description.equals(lineItem.description) : lineItem.description != null) return false;
        return price != null ? price.equals(lineItem.price) : lineItem.price == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String unitPrice = "";
        if (quantity > 1) {
            unitPrice = "@" + price;
        }
            return description + " " + quantity + unitPrice + " " + price.multipliedBy(quantity);
    }

}
