package net.serenitybdd.dojo.supermarket.model;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addFixedDiscount(Product product, int fixedDiscount) {
        double percentage = (double)fixedDiscount/product.getPrice();
        deals.put(product, new Promotion(0, percentage));
    }

    public void addPercentageDiscount(Product product, double percentageDiscount) {
        deals.put(product, new Promotion(0, percentageDiscount));
    }

    private boolean hasDealFor(Product product) {
        return deals.containsKey(product);
    }

    public void addBuyAndGetSomeFreeDeal(int buy, Product product, int getFree) {
        double percentageDiscount = (double)getFree/(double)buy;
        int mustBuyMoreThan = buy - 1;
        deals.put(product, new Promotion(mustBuyMoreThan, percentageDiscount));
    }

    public void addDiscountForQuantityDeal(int mustBuyMoreThan, Product product, double percentageDiscount) {
        deals.put(product, new Promotion(mustBuyMoreThan, percentageDiscount));
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
}
