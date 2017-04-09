package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.receipt.LineItem;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Receipt {

    private Collection<LineItem> lineItems = new ArrayList<>();
    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Collection<LineItem> itemsPurchased() {
        return lineItems;
    }

    void add(Product product) {
        LineItem lineitem = new LineItem(product.getDescription(), product.getPrice());
        if (lineItems.contains(lineitem)) {
            lineItems.iterator().next().incrementQuantity();
        } else {
            lineItems.add(lineitem);
        }
        totalPrice = totalPrice.plus(product.getPrice());
    }

    void applyPromotionToProduct(Promotion promotion, Product product) {
        int quantityScanned = lineItems.iterator().next().getQuantity();
        Money discount = promotion.calculateDiscount(quantityScanned, product.getPrice());
            if (!discount.isZero()) {
                lineItems.add(new LineItem(product.getDescription() + " Promotion", discount));
                totalPrice = totalPrice.plus(discount);
            }

    }

}
