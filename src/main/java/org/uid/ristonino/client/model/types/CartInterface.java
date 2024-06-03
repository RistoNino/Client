package org.uid.ristonino.client.model.types;

public class CartInterface {
    private int quantity;
    private double price;
    private double total = 0.00;

    public CartInterface(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = this.quantity * this.price;
    }
    public double getPrice() {
        return price;
    }
}
