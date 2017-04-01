package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.promotions.BuyAndGetSomeFree;
import net.serenitybdd.dojo.supermarket.model.promotions.DiscountForQuantity;
import net.serenitybdd.dojo.supermarket.model.promotions.FixedDiscount;
import net.serenitybdd.dojo.supermarket.model.promotions.PercentageDiscount;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addFixedDiscount(Product product, int fixedDiscount) {
        Promotion discount = new FixedDiscount(fixedDiscount);
        deals.put(product, discount);
    }

    public void addPercentageDiscount(Product product, double percentageDiscount) {
        deals.put(product, new PercentageDiscount(percentageDiscount));
    }

    public void addBuyAndGetSomeFreeDeal(int buy, Product product, int getFree) {


//        double percentageDiscount = (double)getFree/(double)buy;
//        int mustBuyMoreThan = buy - 1;
//        deals.put(product, new Promotion(mustBuyMoreThan, percentageDiscount));

        deals.put(product, new BuyAndGetSomeFree(buy, getFree));
    }

    public void addDiscountForQuantityDeal(int mustBuyMoreThan, Product product, double percentageDiscount) {
        //deals.put(product, new Promotion(mustBuyMoreThan, percentageDiscount));
        deals.put(product, new DiscountForQuantity(mustBuyMoreThan, percentageDiscount));
    }

//    public void addBuyQuantityForFixedPriceDeal(int mustBuyMoreThan, Product product, int fixedPrice) {
//
//    }

    double priceAfterPromotion(Product product, int quantityOfProduct) {

        double price = product.getPrice();

        if (this.hasDealFor(product)) {
            Promotion promotion = deals.get(product);
            return promotion.applyDiscount(price, quantityOfProduct);
        }
        return price;
    }

    private boolean hasDealFor(Product product) {
        return deals.containsKey(product);
    }
}
