package org.uid.ristonino.client.model.types;

public class CartInterface {
    private int quantity;
    private final double price;

    public CartInterface(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
}
