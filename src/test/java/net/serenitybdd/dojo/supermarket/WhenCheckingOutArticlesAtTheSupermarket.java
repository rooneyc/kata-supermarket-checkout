package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.joda.money.Money;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.00"));
                //equalTo(Money.parse("EUR 0.00")));

    }

    @Test
    public void receipt_should_calculate_total_price_when_one_item_in_cart() throws Exception {

        // GIVEN
        ProductCatalog catalog = new ProductCatalog();
        catalog.add("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 1.20"));

    }

    @Test
    public void receipt_should_list_all_items_purchased() throws Exception {

        // GIVEN
        ProductCatalog catalog = new ProductCatalog();
        catalog.add("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.add("0000000000002", new Product("Bread", Money.parse("EUR 2.40")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));
        theCart.addItem(new Item("0000000000002"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.itemsPurchased()).contains(new LineItem("Milk", Money.parse("EUR 1.20")));
        assertThat(receipt.itemsPurchased()).contains(new LineItem("Bread", Money.parse("EUR 2.40")));


    }
}

