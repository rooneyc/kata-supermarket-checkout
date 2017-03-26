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
    public void a_receipt_should_show_correct_total_price_when_items_on_fixed_discount_deal_in_cart() throws Exception {

        // GIVEN
        Product oranges = new Product("1Kg Bag of Oranges", 5.00); //1.00 discount
        Product banannas = new Product("2Kg Bag of Oranges", 6.00); //2.00 discount
        Catalog catalog = new Catalog();
        catalog.addFixedDiscount(oranges, 1.00);
        catalog.addFixedDiscount(banannas, 2.00);
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(oranges);
        theCart.addItem(banannas);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(8.00);
    }

    //    @Test
//    public void a_receipt_should_show_the_correct_total_price_when_buy_two_get_one_free_items_in_cart() throws Exception {
//        // GIVEN
//        Catalog catalog = new Catalog();
//        Teller teller = new Teller(catalog);
//        ShoppingCart theCart = new ShoppingCart();
//        Product toothBrush = new Product("Toothbrush", 3.50);
//        theCart.addItem(toothBrush);
//        theCart.addItem(toothBrush);
//
//        // WHEN
//        Receipt receipt = teller.checksOutArticlesFrom(theCart);
//
//        // THEN
//        assertThat(receipt.getTotalPrice()).isEqualTo(3.50);
//
//    }

}
