package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.joda.money.Money;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WhenCheckingOutArticlesAtTheSupermarket {

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {

        // GIVEN
        ProductCatalog catalog = new ProductCatalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(Money.parse("EUR 0.00")));

    }

    @Test
    public void receipt_should_calculate_total_price_when_one_item_in_cart() throws Exception {

        // GIVEN
        ProductCatalog catalog = new ProductCatalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product product = new Product("Milk", Money.parse("EUR 1.20"));
        Barcode barcode = new Barcode("0000000000001");
        catalog.add(barcode, product);

        Item item = new Item(barcode);
        theCart.addItem(item);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(Money.parse("EUR 1.20")));

    }
}
