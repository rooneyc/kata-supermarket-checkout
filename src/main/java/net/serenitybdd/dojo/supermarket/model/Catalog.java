package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.promotions.BuyAndGetSomeFree;
import net.serenitybdd.dojo.supermarket.model.promotions.DiscountForQuantity;
import net.serenitybdd.dojo.supermarket.model.promotions.FixedDiscountPerItem;
import net.serenitybdd.dojo.supermarket.model.promotions.PercentageDiscountPerItem;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Product, Promotion> deals = new HashMap<>();

    public void addPromotionForProduct(Product product, Promotion promotion) {
        deals.put(product, promotion);
    }

    public void addFixedDiscount(Product product, int fixedDiscount) {
        Promotion discount = new FixedDiscountPerItem(fixedDiscount);
        deals.put(product, discount);
    }

    public void addPercentageDiscount(Product product, double percentageDiscount) {
        deals.put(product, new PercentageDiscountPerItem(percentageDiscount));
    }

    public void addBuyAndGetSomeFreeDeal(int quantityRequiredToBuy, Product product, int quantityGetFree) {
        deals.put(product, new BuyAndGetSomeFree(quantityRequiredToBuy, quantityGetFree));
    }

    public void addDiscountForQuantityDeal(int mustBuyMoreThan, Product product, double percentageDiscount) {
        deals.put(product, new DiscountForQuantity(mustBuyMoreThan, percentageDiscount));
    }

    public void addBuyQuantityForFixedPriceDeal(int i, Product suncream, int i1) {
    }

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
