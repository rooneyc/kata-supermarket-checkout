package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenCheckingOutArticlesAtTheSupermarket {

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {

        // GIVEN
        Teller teller = new Teller(new Catalog());
        ShoppingCart theCart = new ShoppingCart();

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(0.00);

    }

    @Test
    public void a_receipt_should_show_the_total_price_when_one_item_in_the_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product milk = new Product("Milk", 1.00);
        theCart.addItem(milk);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(milk.getPrice());

    }

    @Test
    public void a_receipt_should_show_the_total_price_when_two_items_of_same_type_in_the_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product milk = new Product("Milk", 1.00);
        theCart.addItem(milk);
        theCart.addItem(milk);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(milk.getPrice()*2);

    }

    @Test
    public void a_receipt_should_show_the_total_price_when_two_items_of_different_types_in_the_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product milk = new Product("Milk", 1.00);
        Product bread = new Product("Bread", 2.00);
        theCart.addItem(milk);
        theCart.addItem(bread);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(3.00);

    }

    @Test
    public void a_receipt_should_show_the_list_of_purchased_items() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product milk = new Product("Milk", 1.00);
        Product bread = new Product("Bread", 2.00);
        theCart.addItem(milk);
        theCart.addItem(bread);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getPurchasedItems()).contains(milk);
        assertThat(receipt.getPurchasedItems()).contains(bread);

    }

    @Test
    public void a_receipt_should_show_the_total_price_for_but_two_get_one_free_special_deal_items() throws Exception {
        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product juice = new Product("Orange Juice", 1.00);
        theCart.addItem(juice);
        theCart.addItem(juice);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(1.00);

    }

}
