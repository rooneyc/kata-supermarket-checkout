package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Item> items = new ArrayList<>();

    List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ShoppingCartAdder add(Item item) {
        return new ShoppingCartAdder(this, item);
    }

    public class ShoppingCartAdder {
        private final ShoppingCart shoppingCart;
        private final Item item;

        private ShoppingCartAdder(ShoppingCart shoppingCart, Item item) {
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
