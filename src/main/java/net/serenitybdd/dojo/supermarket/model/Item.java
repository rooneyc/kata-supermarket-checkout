package net.serenitybdd.dojo.supermarket.model;

public class Item {

    private Barcode barCode;

    public Item(Barcode barCode) {
        this.barCode = barCode;
    }

    Barcode getBarCode() {
        return barCode;
    }
}
