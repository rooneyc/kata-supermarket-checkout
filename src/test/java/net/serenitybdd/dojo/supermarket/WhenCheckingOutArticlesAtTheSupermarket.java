package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import net.serenitybdd.dojo.supermarket.model.promotion.*;
import net.serenitybdd.dojo.supermarket.model.Article;
import net.serenitybdd.dojo.supermarket.model.receipt.LineItem;
import net.serenitybdd.dojo.supermarket.model.Receipt;
import org.joda.money.Money;
import org.junit.Test;

import java.util.Collection;

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
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.00"));

    }

    @Test
    public void receipt_should_calculate_total_price_when_one_item_in_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Article("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
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
        theCart.addItem(new Article("0000000000001"));
        theCart.addItem(new Article("0000000000002"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
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
        theCart.addItem(new Article("0000000000001"));
        theCart.addItem(new Article("0000000000002"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
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
        theCart.addItem(new Article("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
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
        theCart.addItem(new Article("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
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
        theCart.addItem(new Article("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Milk Promotion");
        assertThat(receipt.print()).contains("EUR -0.30");
    }

    @Test
    public void a_line_item_should_display_quantity_of_product_type_purchased() throws Exception {

        // GIVEN
        LineItem lineItem = new LineItem("Apple", Money.parse("EUR 0.30"));

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        assertThat(lineString).isEqualTo("Apple 1 EUR 0.30");

    }

    @Test
    public void if_scan_two_items_of_same_type_then_line_item_should_have_quantity_of_two() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Total EUR 2.40");

    }

    @Test
    public void receipt_should_show_buy_two_get_one_free_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new BuyToGetFree(2, 1));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 2.40"));

    }

    @Test
    public void should_be_able_to_print_receipt() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new BuyToGetFree(2, 1));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Total EUR 2.40");

    }

    @Test
    public void receipt_should_show_buy_four_get_one_free_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new BuyToGetFree(4, 1));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(5);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 4.80"));

    }

    @Test
    public void receipt_should_show_buy_more_than_quantity_to_get_percentage_discount() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000003", new Product("Apple", Money.parse("EUR 0.30")));
        int quantityMustBuyMoreThan = 10;
        catalog.addPromotion("0000000000003", new BuyMoreThanToGetPercentageDiscount(quantityMustBuyMoreThan, 0.20));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000003")).times(quantityMustBuyMoreThan+1);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 2.64"));

    }

    @Test
    public void receipt_should_show_quantity_for_set_price_discount() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new BuyQuantityForSetPriceDiscount(2, Money.parse("EUR 2.00")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 2.00"));

    }

    @Test
    public void line_items_should_show_correct_quantity_when_multiples_of_more_than_one_product() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct("0000000000002", new Product("Bread", Money.parse("EUR 2.40")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);
        theCart.add(new Article("0000000000002")).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Bread 2");

    }

    @Test
    public void receipt_should_be_able_to_apply_two_different_types_of_quantity_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct("0000000000001", new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion("0000000000001", new BuyToGetFree(4, 1));
        catalog.addProduct("0000000000003", new Product("Apple", Money.parse("EUR 0.30")));
        catalog.addPromotion("0000000000003", new BuyMoreThanToGetPercentageDiscount(10, 0.20));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(5);
        theCart.add(new Article("0000000000003")).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 7.44"));

    }

    //TODO If Buy less than required should not get the promotion
    //TODO Should only get promotion once if buy more than required.
    //TODO Line Items should should price total price per line
    //TODO Should be able locate LineItem by Product?
    //TODO Receipt should print number of items purchased
    //TODO LineItem Items need a Header LineItem

}


