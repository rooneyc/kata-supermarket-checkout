package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenCheckingOutArticlesAtTheSupermarket {

    Catalog catalog;

    Teller teller;

    ShoppingCart theCart;


    @Before
    public void setup() {
        catalog = new Catalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart();
    }

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(0.00);

    }

    @Test
    public void a_receipt_should_show_the_total_price_when_one_item_in_the_cart() throws Exception {

        // GIVEN
        Product milk = new Product("Milk", 100);
        theCart.addItem(milk);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(1.00);

    }

    @Test
    public void a_receipt_should_show_the_total_price_when_several_items_of_same_type_in_the_cart() throws Exception {

        // GIVEN
        Product milk = new Product("Milk", 100);
        theCart.add(milk).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(3.00);

    }

    @Test
    public void receipt_total_should_account_for_two_items_of_different_types_in_the_cart() throws Exception {

        // GIVEN
        Product milk = new Product("Milk", 100);
        Product bread = new Product("Bread", 200);
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
        Product milk = new Product("Milk", 100);
        Product bread = new Product("Bread", 200);
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
        Product oranges = new Product("1Kg Bag of Oranges", 500);
        catalog.addFixedDiscount(oranges, 100);
        theCart.addItem(oranges);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(4.00);
    }

    @Test
    public void receipt_total_should_account_for_several_items_in_cart_on_fixed_discount_deal() throws Exception {

        // GIVEN
        Product oranges = new Product("1Kg Bag of Oranges", 500);
        Product bananas = new Product("2Kg Bag of Oranges", 600);
        Product grapes = new Product("0.5Kg Bag of Oranges", 300);
        catalog.addFixedDiscount(oranges, 100);
        catalog.addFixedDiscount(bananas, 200);
        catalog.addFixedDiscount(grapes, 50);
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
        Product rice = new Product("1Kg Bag of Rice", 1000);
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
        Product rice = new Product("1Kg Bag of Rice", 1000);
        Product biscuits = new Product("Colourful Cakes", 560);
        Product tea = new Product("Tea Bags", 2000);
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
        Product apple = new Product("Apple", 50);
        catalog.addDiscountForQuantityDeal(10, apple, 0.20);
        theCart.add(apple).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(4.40);

    }

    @Test
    public void receipt_total_should_account_for_several_items_in_cart_on_percentage_discount_for_quantity_deal() throws Exception {

        // GIVEN
        Product apple = new Product("Apple", 50);
        Product cheese = new Product("Cheese", 450);
        catalog.addDiscountForQuantityDeal(10, apple, 0.20);
        catalog.addDiscountForQuantityDeal(3, cheese, 0.40);
        theCart.add(apple).times(11);
        theCart.add(cheese).times(4);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(15.2);

    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_buy_two_get_one_free_deal() throws Exception {

        // GIVEN
        Product toothBrush = new Product("Toothbrush", 350);
        catalog.addBuyAndGetSomeFreeDeal(2, toothBrush,1);
        theCart.add(toothBrush).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(3.50);

    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_buy_four_get_one_free_deal() throws Exception {

        // GIVEN
        Product floss = new Product("Floss", 150);
        catalog.addBuyAndGetSomeFreeDeal(4, floss,2);
        theCart.add(floss).times(4);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(3.00);

    }

    @Test
    public void receipt_should_account_for_several_special_deals() throws Exception {

        // GIVEN
        Product toothBrush = new Product("Toothbrush", 350);
        catalog.addBuyAndGetSomeFreeDeal(2, toothBrush, 1);
        theCart.add(toothBrush).times(2);

        Product floss = new Product("Toothbrush", 150);
        catalog.addBuyAndGetSomeFreeDeal(4, floss, 1);
        theCart.add(floss).times(4);

        Product apple = new Product("Apple", 50);
        Promotion deal = new Promotion(10, 0.20);
        catalog.addDiscountForQuantityDeal(10, apple, 0.20);
        theCart.add(apple).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(12.40);

    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_buy_multiple_for_set_price_deal() throws Exception {

        // GIVEN
        Product suncream = new Product("Suncream", 1700);
        catalog.addBuyQuantityForFixedPriceDeal(2, suncream, 3000);
        theCart.add(suncream).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(30.00);

    }

    //should not scan items not in catalogue (i.e. bought in another shop)

     //any 5 chocolate bars for £2

    //coupons

    //Buy item X and get  % discount on item Y

    //Save X amount when you spend Y amount or more - Voucher

    //mixed item deal e.g. valentines meal, dinner + desert + wine for X

    //free catalogue item e.g. friday night deal buy 2 x bear and 1 x pizza get 1 box salted crackers free

    //free gift (not in catalogue) i.e. free CD with sunday paper

    //offer combination rules i.e. this offer cannot be combined with an other offer e.g. coupons

    //if product qualifies for two deals, most beneficial deal should apply

    //Free delivery for all orders over X amount

    //delivery costs change per timslot

    //

}

