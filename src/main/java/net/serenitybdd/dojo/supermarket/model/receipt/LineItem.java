package net.serenitybdd.dojo.supermarket.model.receipt;

import org.joda.money.Money;

public class LineItem implements CanGetAndIncrementQuantity {

    private String description;
    private int quantity = 1;
    private Money price;

    public LineItem(String description, Money price) {
        this.description = description;
        this.price = price;
    }

    public void incrementQuantity() {
        quantity += 1;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

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
        if (quantity > 1) {
            return description + " " + quantity + "@" + price + " " + price.multipliedBy(quantity);
        }
            return description + " " + quantity               + " " + price.multipliedBy(quantity);
    }

}
