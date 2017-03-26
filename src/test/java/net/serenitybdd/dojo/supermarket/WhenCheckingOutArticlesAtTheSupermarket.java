package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

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
        catalog.add(milk);
        theCart.addItem(milk);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(milk.getPrice());

    }

//    @Test
//    public void a_receipt_should_show_the_total_price_when_two_items_of_same_type_in_the_cart() throws Exception {
//
//        // GIVEN
//        Catalog catalog = new Catalog();
//        Teller teller = new Teller(catalog);
//        ShoppingCart theCart = new ShoppingCart();
//        Product milk = new Product();
//        catalog.setProductPrice(milk, 1.00);
//        theCart.addItem(milk);
//        theCart.addItem(milk);
//
//        // WHEN
//        Receipt receipt = teller.checksOutArticlesFrom(theCart);
//
//        // THEN
//        assertThat(receipt.getTotalPrice()).isEqualTo(catalog.getProductPrice(milk)*2);
//
//    }
//
//    @Test
//    public void a_receipt_should_show_the_total_price_when_two_items_of_different_types_in_the_cart() throws Exception {
//
//        // GIVEN
//        Catalog catalog = new Catalog();
//        Teller teller = new Teller(catalog);
//        ShoppingCart theCart = new ShoppingCart();
//        Product milk = new Product();
//        Product bread = new Product();
//        catalog.setProductPrice(milk, 1.00);
//        catalog.setProductPrice(bread, 2.00);
//        theCart.addItem(milk);
//        theCart.addItem(bread);
//
//        // WHEN
//        Receipt receipt = teller.checksOutArticlesFrom(theCart);
//
//        // THEN
//        assertThat(receipt.getTotalPrice()).isEqualTo(3.00);
//
//    }
}
