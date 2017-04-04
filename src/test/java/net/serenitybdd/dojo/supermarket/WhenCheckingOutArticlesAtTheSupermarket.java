package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import net.serenitybdd.dojo.supermarket.model.promotion.FixedPriceDiscountPerItem;
import net.serenitybdd.dojo.supermarket.model.promotion.PercentageDiscountPerItem;
import net.serenitybdd.dojo.supermarket.model.Item;
import net.serenitybdd.dojo.supermarket.model.receipt.LineItem;
import net.serenitybdd.dojo.supermarket.model.Receipt;
import org.joda.money.Money;
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
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.00"));
                //equalTo(Money.parse("EUR 0.00")));

    }

    @Test
    public void receipt_should_calculate_total_price_when_one_item_in_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));

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
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct("0000000000002", new Product("Bread", Money.parse("EUR 2.40")));

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

    @Test
    public void receipt_should_calculate_total_price_when_two_items_in_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct("0000000000002", new Product("Bread", Money.parse("EUR 2.40")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));
        theCart.addItem(new Item("0000000000002"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 3.60"));

    }

    @Test
    public void receipt_should_show_fixed_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new FixedPriceDiscountPerItem(Money.parse("EUR 0.30")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.90"));

    }

    @Test
    public void receipt_should_show_percentage_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new PercentageDiscountPerItem(0.30));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.84"));

    }

    @Test
    public void should_be_able_print_receipt_line_item() throws Exception {

        // GIVEN
        LineItem lineItem = new LineItem("Milk",  Money.parse("EUR 1.20"));

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        assertThat(lineString).isEqualTo("Milk 1 EUR 1.20");

    }

    @Test
    public void receipt_should_show_promotion_line_item() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new FixedPriceDiscountPerItem(Money.parse("EUR 0.30")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Item("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.itemsPurchased()).contains(new LineItem("Milk Promotion", Money.parse("EUR -0.30")));

    }

    @Test
    public void a_line_should_display_quantity_of_product_type_purchased() throws Exception {

        // GIVEN
        LineItem lineItem = new LineItem("Apple", Money.parse("EUR 0.30"));

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        assertThat(lineString).isEqualTo("Apple 1 EUR 0.30");

    }

    //TODO Rename LineItem to LineItem
    //TODO Two products of the same type should result in line item with quantity of 2
    //TODO LineItem Items need a Header LineItem


}

