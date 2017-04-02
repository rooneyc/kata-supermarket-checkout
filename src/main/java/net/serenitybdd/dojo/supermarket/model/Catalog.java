package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.promotion.NullPromotion;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<String, Product> products = new HashMap<>();
    private Map<String, Promotion> promotions = new HashMap<>();

    Product getProductFromCode(String code) {
        return products.get(code);
    }

    public void addProduct(String code, Product product) {
        products.put(code, product);
    }

    public void addPromotion(String code, Promotion promotion) {
        promotions.put(code, promotion);
    }

    public Promotion getPromotionFromCode(String code) {
        return promotions.getOrDefault(code, new NullPromotion());
    }
}
