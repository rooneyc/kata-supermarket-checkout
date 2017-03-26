package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void a_receipt_should_show_the_total_price_when_several_items_of_same_type_in_the_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product milk = new Product("Milk", 1.00);
        theCart.add(milk).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(milk.getPrice()*3);

    }

    @Test
    public void receipt_total_should_account_for_two_items_of_different_types_in_the_cart() throws Exception {

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
    public void receipt_total_should_account_for_item_in_cart_on_fixed_discount_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product oranges = new Product("1Kg Bag of Oranges", 5.00);
        catalog.addFixedDiscount(oranges, 1.00);
        theCart.addItem(oranges);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(4.00);
    }

    @Test
    public void receipt_total_should_account_for_several_items_in_cart_on_fixed_discount_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product oranges = new Product("1Kg Bag of Oranges", 5.00);
        Product bananas = new Product("2Kg Bag of Oranges", 6.00);
        Product grapes = new Product("0.5Kg Bag of Oranges", 3.00);
        catalog.addFixedDiscount(oranges, 1.00);
        catalog.addFixedDiscount(bananas, 2.00);
        catalog.addFixedDiscount(grapes, 0.50);
        theCart.addItem(oranges);
        theCart.addItem(bananas);
        theCart.addItem(grapes);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(10.50);
    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_percentage_discount_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product rice = new Product("1Kg Bag of Rice", 10.00);
        catalog.addPercentageDiscount(rice, 0.10);
        theCart.addItem(rice);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(9.00);
    }

    @Test
    public void receipt_total_should_account_for_several_items_in_cart_on_percentage_discount_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product rice = new Product("1Kg Bag of Rice", 10.00);
        Product biscuits = new Product("Jaffa Cakes", 5.60);
        Product tea = new Product("Tea Bags", 20.00);
        catalog.addPercentageDiscount(rice, 0.10);
        catalog.addPercentageDiscount(biscuits, 0.15);
        catalog.addPercentageDiscount(tea, 0.20);
        theCart.addItem(rice);
        theCart.addItem(biscuits);
        theCart.addItem(tea);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(29.76);
    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_percentage_discount_for_quantity_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product apple = new Product("Apple", 0.50);
        Promotion deal = new Promotion(10, 0.20);
        catalog.addDeal(apple, deal);
        theCart.add(apple).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(4.40);

    }

    @Test
    public void receipt_total_should_account_for_several_items_in_cart_on_percentage_discount_for_quantity_deal() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product apple = new Product("Apple", 0.50);
        Promotion deal = new Promotion(10, 0.20);
        catalog.addDeal(apple, deal);
        theCart.add(apple).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(4.40);

    }

    @Test
    public void receipt_total_should_account_for_items_in_cart_on_buy_two_get_one_free_deal() throws Exception {
        // GIVEN
        Catalog catalog = new Catalog();
        Teller teller = new Teller(catalog);
        ShoppingCart theCart = new ShoppingCart();
        Product toothBrush = new Product("Toothbrush", 3.50);
        catalog.addBuyGetFreeDeal(toothBrush, 2, 1);
        theCart.addItem(toothBrush);
        theCart.addItem(toothBrush);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(3.50);

    }

    //if product in two deals, most beneficial deal should apply

}
