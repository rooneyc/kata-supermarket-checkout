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
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(Money.parse("USD 0.00")));

    }
}
