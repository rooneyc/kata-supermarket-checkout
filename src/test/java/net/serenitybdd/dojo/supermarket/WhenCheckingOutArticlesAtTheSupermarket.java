package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import net.serenitybdd.dojo.supermarket.model.catalog.Code;
import net.serenitybdd.dojo.supermarket.model.promotion.*;
import net.serenitybdd.dojo.supermarket.model.Article;
import net.serenitybdd.dojo.supermarket.model.receipt.ProductLineItem;
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
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 0.00"));
    }

    @Test
    public void receipt_should_calculate_total_price_when_one_item_in_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct(new Code("0000000000002"), new Product("Bread", Money.parse("EUR 2.40")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Article("0000000000001"));
        theCart.addItem(new Article("0000000000002"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Milk");
        assertThat(receipt.print()).contains("Bread");
    }

    @Test
    public void receipt_should_calculate_total_price_when_two_items_in_cart() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct(new Code("0000000000002"), new Product("Bread", Money.parse("EUR 2.40")));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new FixedPriceDiscountPerItem(Money.parse("EUR 0.30")));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new PercentageDiscountPerItem(0.30));

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
        Product milk = new Product("Milk",  Money.parse("EUR 1.20"));
        ProductLineItem lineItem = new ProductLineItem(milk);

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        System.out.println(lineString);
        assertThat(lineString).isEqualTo("Milk 1 EUR 1.20");
    }

    @Test
    public void receipt_should_show_promotion_line_item() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new FixedPriceDiscountPerItem(Money.parse("EUR 0.30")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addItem(new Article("0000000000001"));

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Milk");
        assertThat(receipt.print()).contains("EUR -0.30");
    }

    @Test
    public void a_line_item_should_display_quantity_of_product_type_purchased() throws Exception {

        // GIVEN
        Product apple = new Product("Apple", Money.parse("EUR 0.30"));
        ProductLineItem lineItem = new ProductLineItem(apple);

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        System.out.println(lineString);
        assertThat(lineString).contains("1");
    }

    @Test
    public void if_scan_two_items_of_same_type_then_line_item_should_have_quantity_of_two() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyToGetFree(2, 1));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyToGetFree(2, 1));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyToGetFree(4, 1));

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
        catalog.addProduct(new Code("0000000000003"), new Product("Apple", Money.parse("EUR 0.30")));
        int quantityMustBuyMoreThan = 10;
        catalog.addPromotion(new Code("0000000000003"), new BuyMoreThanToGetPercentageDiscount(quantityMustBuyMoreThan, 0.20));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyQuantityForSetPriceDiscount(2, Money.parse("EUR 2.00")));

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
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct(new Code("0000000000002"), new Product("Bread", Money.parse("EUR 2.40")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);
        theCart.add(new Article("0000000000002")).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("2");
    }

    @Test
    public void receipt_should_be_able_to_apply_two_different_types_of_quantity_discounts() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyToGetFree(4, 1));
        catalog.addProduct(new Code("0000000000003"), new Product("Apple", Money.parse("EUR 0.30")));
        catalog.addPromotion(new Code("0000000000003"), new BuyMoreThanToGetPercentageDiscount(10, 0.20));

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

    @Test
    public void receipt_should_show_number_of_items() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addProduct(new Code("0000000000002"), new Product("Bread", Money.parse("EUR 2.40")));
        catalog.addProduct(new Code("0000000000003"), new Product("Apple", Money.parse("EUR 0.30")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);
        theCart.addItem(new Article("0000000000002"));
        theCart.add(new Article("0000000000003")).times(11);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("14");
    }

    @Test
    public void a_line_item_should_display_total_price_for_quantity() throws Exception {

        // GIVEN
        Product apple = new Product("Apple", Money.parse("EUR 0.30"));
        ProductLineItem lineItem = new ProductLineItem(apple);
        lineItem.incrementQuantity();

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        System.out.println(lineString);
        assertThat(lineString).contains("EUR 0.60");
    }

    @Test
    public void a_line_item_should_display_unit_price_when_quantity_greater_than_one() throws Exception {

        // GIVEN
        Product apple = new Product("Apple", Money.parse("EUR 0.30"));
        ProductLineItem lineItem = new ProductLineItem(apple);
        lineItem.incrementQuantity();

        // WHEN
        String lineString = lineItem.toString();

        // THEN
        System.out.println(lineString);
        assertThat(lineString).contains("@EUR 0.30");
    }

    @Test
    public void receipt_should_show_correct_number_of_items_when_promotion() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyQuantityForSetPriceDiscount(2, Money.parse("EUR 2.00")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Items 2");
    }

    @Test
    public void should_only_get_buy_two_get_one_free_discounts_once_for_four_articles() throws Exception {

        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new BuyToGetFree(2, 1));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(4);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.getTotalPrice()).isEqualTo(Money.parse("EUR 3.60"));
    }

    @Test
    public void promotion_line_item_should_display_promotion_details() throws Exception {
        // GIVEN
        Catalog catalog = new Catalog();
        catalog.addProduct(new Code("0000000000001"), new Product("Milk", Money.parse("EUR 1.20")));
        catalog.addPromotion(new Code("0000000000001"), new FixedPriceDiscountPerItem(Money.parse("EUR 0.30")));
        catalog.addProduct(new Code("0000000000002"), new Product("Bread", Money.parse("EUR 2.40")));
        catalog.addPromotion(new Code("0000000000002"), new PercentageDiscountPerItem(0.30));
        catalog.addProduct(new Code("0000000000003"), new Product("Apple", Money.parse("EUR 0.30")));
        catalog.addPromotion(new Code("0000000000003"), new BuyMoreThanToGetPercentageDiscount(10, 0.20));
        catalog.addProduct(new Code("0000000000004"), new Product("Honey", Money.parse("EUR 3.50")));
        catalog.addPromotion(new Code("0000000000004"), new BuyToGetFree(2, 1));
        catalog.addProduct(new Code("0000000000005"), new Product("Toothbrush", Money.parse("EUR 7.50")));
        catalog.addPromotion(new Code("0000000000005"), new BuyQuantityForSetPriceDiscount(2, Money.parse("EUR 6.00")));

        Teller teller = new Teller(catalog);

        ShoppingCart theCart = new ShoppingCart();
        theCart.add(new Article("0000000000001")).times(2);
        theCart.add(new Article("0000000000002")).times(2);
        theCart.add(new Article("0000000000003")).times(11);
        theCart.add(new Article("0000000000004")).times(3);
        theCart.add(new Article("0000000000005")).times(2);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        System.out.println(receipt.print());
        assertThat(receipt.print()).contains("Buy 2 Get 1 Free");
        assertThat(receipt.print()).contains("Discounted By");
        assertThat(receipt.print()).contains("% Off");
        assertThat(receipt.print()).contains("When Buy More Than");
        assertThat(receipt.print()).contains("For");
    }



    //TODO LineItems should have fixed spacing
    //TODO Receipts need a Header ProductLineItem
}



