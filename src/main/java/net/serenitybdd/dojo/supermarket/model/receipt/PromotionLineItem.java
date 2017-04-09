package net.serenitybdd.dojo.supermarket.model.receipt;

import org.joda.money.Money;

public class PromotionLineItem implements LineItem {

    private String description;
    private Money discount;

    public PromotionLineItem(String description, Money discount) {
        this.description = description;
        this.discount = discount;
    }

    @Override
    public void incrementQuantity() {}

    @Override
    public int getQuantity() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionLineItem lineItem = (PromotionLineItem) o;

        if (description != null ? !description.equals(lineItem.description) : lineItem.description != null) return false;
        return discount != null ? discount.equals(lineItem.discount) : lineItem.discount == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return description + " " + "Promotion" + " " + discount;
    }

}
