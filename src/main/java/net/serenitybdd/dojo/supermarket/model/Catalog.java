package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.model.catalog.Code;
//import net.serenitybdd.dojo.supermarket.model.catalog.Entry;
import net.serenitybdd.dojo.supermarket.model.promotion.NullPromotion;
//import org.joda.money.Money;

//import java.util.concurrent.atomic.AtomicInteger;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<Code, Product> products = new HashMap<>();
    private Map<Code, Promotion> promotions = new HashMap<>();

    Product getProduct(Code code) {
        return products.get(code);
    }

    public void addProduct(Code code, Product product) {
        products.put(code, product);
    }

    public void addPromotion(Code code, Promotion promotion) {
        promotions.put(code, promotion);
    }

    Promotion getPromotion(Code code) {
        return promotions.getOrDefault(code, new NullPromotion());
    }

//    private static AtomicInteger nextCode = new AtomicInteger();

//    private Map<Code, Entry> entries = new HashMap<>();

//    public void addProduct(Code code, Product product) {
//        entries.put(code, new Entry(product, getPromotion(code)));
//    }
//
//    Product getProduct(Code code) {
//        return getEntryFromCode(code).getProduct();
//    }
//
//    public void addPromotion(Code code, Promotion promotion) {
//        entries.put(code, new Entry(getProduct(code),promotion));
//    }
//
//    Promotion getPromotion(Code code) {
//        return getEntryFromCode(code).getPromotion();
//    }
//
//    private Entry getEntryFromCode(Code code) {
//        return entries.getOrDefault(code, new Entry(new Product("", Money.parse("EUR 0.00")), new NullPromotion()));
//    }

}
