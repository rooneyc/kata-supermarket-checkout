package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.receipt.Line;
import net.serenitybdd.dojo.supermarket.model.receipt.TransactionType;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Collection;

public class Receipt {

    private Collection<Line> lineItems = new ArrayList<>();
    private Money totalPrice = Money.parse("EUR 0.00");

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Collection<Line> itemsPurchased() {
        return lineItems;
    }

    public void add(Product product) {
//        lineItems.add(new Line (product.getDescription(), product.getPrice(), TransactionType.DEBIT));
        lineItems.add(new Line (product.getDescription(), product.getPrice()));
        totalPrice = totalPrice.plus(product.getPrice());
    }

    public void applyPromotionToProduct(Promotion promotion, Product product) {
            Money discount = promotion.calculateDiscount(product.getPrice());
//            lineItems.add(new Line(product.getDescription() + " Promotion", discount, TransactionType.CREDIT));
            lineItems.add(new Line(product.getDescription() + " Promotion", discount));
            totalPrice = totalPrice.plus(discount);
    }

}
