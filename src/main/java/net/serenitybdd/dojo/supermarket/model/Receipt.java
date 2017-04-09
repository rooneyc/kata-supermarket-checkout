package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.receipt.CanIncrementQuantity;
import net.serenitybdd.dojo.supermarket.model.receipt.LineItem;
import net.serenitybdd.dojo.supermarket.model.receipt.NullLineItem;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private final Collection<LineItem> lineItems = new ArrayList<>();
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
            getLineItemForProduct(product).incrementQuantity();
        } else {
            lineItems.add(lineitem);
        }
        totalPrice = totalPrice.plus(product.getPrice());
    }

    private CanIncrementQuantity getLineItemForProduct(Product product) {
        for (LineItem item : lineItems) {
            if (item.equals(new LineItem(product.getDescription(), product.getPrice()))) {
                return item;
            }
        }
        return new NullLineItem();
    }

    void applyPromotionToProduct(Promotion promotion, Product product) {
        int quantityScanned = lineItems.iterator().next().getQuantity();
        Money discount = promotion.calculateDiscount(quantityScanned, product.getPrice());
            if (!discount.isZero()) {
                lineItems.add(new LineItem(product.getDescription() + " Promotion", discount));
                totalPrice = totalPrice.plus(discount);
            }

    }

    public String print() {
        StringBuilder receiptBuilder = new StringBuilder();
        for (LineItem item : lineItems) {
            receiptBuilder.append(item.toString());
            receiptBuilder.append(System.getProperty("line.separator"));
        }
        receiptBuilder.append("Total ");
        receiptBuilder.append(totalPrice);
        return receiptBuilder.toString();
    }
}
