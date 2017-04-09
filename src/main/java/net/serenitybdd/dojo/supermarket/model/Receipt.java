package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.receipt.LineItem;
import net.serenitybdd.dojo.supermarket.model.receipt.ProductLineItem;
import net.serenitybdd.dojo.supermarket.model.receipt.NullLineItem;
import net.serenitybdd.dojo.supermarket.model.receipt.PromotionLineItem;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private final Collection<LineItem> lineItems = new ArrayList<>();
    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    void add(Product product) {
        ProductLineItem lineItem = new ProductLineItem(product.getDescription(), product.getPrice());

        if (lineItems.contains(lineItem)) {
            getLineItemForProduct(product).incrementQuantity();
        } else {
            lineItems.add(lineItem);
        }
        totalPrice = totalPrice.plus(product.getPrice());
    }

    private LineItem getLineItemForProduct(Product product) {
        for (LineItem item : lineItems) {
            if (item.equals(new ProductLineItem(product.getDescription(), product.getPrice()))) {
                return item;
            }
        }
        return new NullLineItem();
    }

    void applyPromotionToProduct(Promotion promotion, Product product) {
        int quantityScanned = getLineItemForProduct(product).getQuantity();
        Money discount = promotion.calculateDiscount(quantityScanned, product.getPrice());
            if (!discount.isZero()) {
                lineItems.add(new PromotionLineItem(product.getDescription(), discount));
                totalPrice = totalPrice.plus(discount);
            }

    }

    public String print() {
        int numberOfItems = 0;
        StringBuilder receiptBuilder = new StringBuilder();
        for (LineItem item : lineItems) {
            receiptBuilder.append(item.toString());
            receiptBuilder.append(System.getProperty("line.separator"));
            numberOfItems += item.getQuantity();
        }
        receiptBuilder.append("Total ");
        receiptBuilder.append(totalPrice);
        receiptBuilder.append(System.getProperty("line.separator"));
        receiptBuilder.append("Number of Items ");
        receiptBuilder.append(numberOfItems);
        return receiptBuilder.toString();
    }
}
