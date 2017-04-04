package net.serenitybdd.dojo.supermarket.model.receipt;

import org.joda.money.Money;

public class Line {

    private String description;
    private Money price;
    //private TransactionType type;

    //public Line(String description, Money price, TransactionType type) {
    public Line(String description, Money price) {
        this.description = description;
        this.price = price;
        //this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (description != null ? !description.equals(line.description) : line.description != null) return false;
        return price != null ? price.equals(line.price) : line.price == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return description + " " + price;
    }
}
