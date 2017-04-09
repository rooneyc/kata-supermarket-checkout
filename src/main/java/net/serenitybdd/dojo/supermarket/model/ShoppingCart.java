package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Article> items = new ArrayList<>();

    List<Article> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Article item) {
        items.add(item);
    }

    public ShoppingCartAdder add(Article item) {
        return new ShoppingCartAdder(this, item);
    }

    public class ShoppingCartAdder {
        private final ShoppingCart shoppingCart;
        private final Article item;

        private ShoppingCartAdder(ShoppingCart shoppingCart, Article item) {
            this.shoppingCart = shoppingCart;
            this.item = item;
        }

        public void times(int quantity) {
            for(int count = 0; count < quantity; count++) {
                shoppingCart.addItem(item);
            }
        }
    }
}
