package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenCheckingOutArticlesAtTheSupermarket {

    private Catalog catalog;

    private Teller teller;

    private ShoppingCart theCart;


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
        theCart.add(toothBrush).times(3);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(7.00);
        assertThat(receipt.numberofItemsSold()).isEqualTo(3);

    }

    @Test
    public void receipt_total_should_account_for_item_in_cart_on_buy_four_get_one_free_deal() throws Exception {

        // GIVEN
        Product floss = new Product("Floss", 150);
        catalog.addBuyAndGetSomeFreeDeal(4, floss,2);
        theCart.add(floss).times(6);

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(6.00);
        assertThat(receipt.numberofItemsSold()).isEqualTo(6);

    }

    @Test
    public void receipt_should_account_for_several_special_deals() throws Exception {

        // GIVEN
        Product toothBrush = new Product("Toothbrush", 350);
        catalog.addBuyAndGetSomeFreeDeal(2, toothBrush, 1);
        theCart.add(toothBrush).times(3); //7

        Product floss = new Product("Toothbrush", 150);
        catalog.addBuyAndGetSomeFreeDeal(4, floss, 1);
        theCart.add(floss).times(5); //6

        Product apple = new Product("Apple", 50);
        Promotion deal = new Promotion(10, 0.20);
        catalog.addDiscountForQuantityDeal(10, apple, 0.20);
        theCart.add(apple).times(11); //4.4

        // WHEN
        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice()).isEqualTo(17.40);

    }

//    @Test
//    public void receipt_total_should_account_for_item_in_cart_on_buy_multiple_for_set_price_deal() throws Exception {
//
//        // GIVEN
//        Product suncream = new Product("Suncream", 1700);
//        catalog.addBuyQuantityForFixedPriceDeal(2, suncream, 3000);
//        theCart.add(suncream).times(3);
//
//        // WHEN
//        Receipt receipt = teller.checksOutArticlesFrom(theCart);
//
//        // THEN
//        assertThat(receipt.getTotalPrice()).isEqualTo(30.00);
//
//    }

    //each promotion modelled as class
    //apply promotion per group
    //joda money
    //how did I get away with no hashcode, equals?

    //should not scan items not in catalogue (i.e. bought in another shop)

    //any 5 chocolate bars for £2

    //coupons
    //http://2.bp.blogspot.com/-ipLVms6ubJI/TYFK72YzSLI/AAAAAAAABs4/7xvT5WIAAoM/s1600/publix%2Breceipt%2B3-15-11.JPG

    //Buy item X and get  % discount on item Y

    //Save X amount when you spend Y amount or more - Voucher

    //mixed item deal e.g. valentines meal, dinner + desert + wine for X

    //free catalogue item e.g. friday night deal buy 2 x bear and 1 x pizza get 1 box salted crackers free

    //free gift (not in catalogue) i.e. free CD with sunday paper

    //offer combination rules i.e. this offer cannot be combined with an other offer e.g. coupons

    //if product qualifies for two deals, most beneficial deal should apply

    //Free delivery for all orders over X amount

    //delivery costs change per timeslot

    //compare item price to other supermarkets and calculate savings

    //Substitutions
    //http://gallery.hd.org/_exhibits/food/_more2007/_more10/Tesco-supermarket-online-order-paper-receipt-at-delivery-extract-mono-2-DHD.png

    //Categories
    //http://alexpoole.info/blog/supermarket-receipt-with-categories/

    //remove items that scanned then deleted

    //Confirm age before buying Alcohol
    //http://gallery.hd.org/_exhibits/bizarre/_more2008/_more03/supermarket-receipt-age-check-21-for-garlic-bread-or-newspaper-mono-1-DHD.gif

    //Apply Promotion as go along?
    //https://qph.ec.quoracdn.net/main-qimg-1abf0c5608a3ed647285b607f6035c0b-c?convert_to_webp=true
    //http://c8.alamy.com/comp/D52GT4/supermarket-shopping-bill-till-receipt-showing-multi-buy-savings-important-D52GT4.jpg
    //http://theharristeeterdeals.com/wp-content/uploads/2016/05/13234964_10156902145845111_347214210_o-2-e1463599717842.jpg
    //http://whatsyourdeal.com/grocery-coupons/wp-content/uploads/2017/02/20170206_143556.jpg
    //https://www.shopandscan.com/receipts/images/sas_tillreceipt1.bmp

    //Apply Promotion at end?
    //https://walaafadul.files.wordpress.com/2016/01/img_0001-1.jpg
    //http://www.yourlocalguardian.co.uk/resources/images/2493669.jpg?type=articleLandscape
    //http://i.imgur.com/zY4R9Yxl.jpg

    //D and C
    //https://onepoundperday.files.wordpress.com/2015/05/onepoundperday_receipt_2015.png
    //or + and -?


    //Show barcodes (I think its confusing but legal?
    //http://images2.wikia.nocookie.net/__cb20111106201149/groceryreceipts/images/2/22/33612.png

    //Show VAT codes?
    //http://3.bp.blogspot.com/_vcw3KJyvgv0/TGxT8dhvYBI/AAAAAAAAARY/VyI93KTb9_E/s1600/IMG.jpg

    //Group multiples of same product?
    //https://normalness.com/wp-content/uploads//2015/09/Woolworths-Receipt.jpg
    //https://homeschoolingwithguinever.files.wordpress.com/2009/11/meijer-receipt.jpg?w=455

    //Nutritional Data
    //https://static.dezeen.com/uploads/2016/02/solution-obesity-hayden-peek-packaging-receipt-design_dezeen_1568_0.jpg

    //points for vouchers

    //X for Y cheapest free e.g. veg 3 for 2 cheapest free

    //Thoughts
    //Buy x get Y free = X+Y for the price of X
    //If advertised as buy 2 apples get 1 free what happens if customer only put 2 apples in cart?
    //Can customer complain to say, retailer did not give customer free apple when checking out?
    //Safer to phrase as get 3 apples for the price of 2, makes it clear that customer has to put 2 apples in cart.
    //Or shop could wrap 3 apples in plastic and buy 2 get 1 free

    //http://supermarketjustice.com/avanza-receipt.jpg
    //https://fizzleblog.files.wordpress.com/2010/12/grocery-receipt-2.jpg?w=756
}

