package org.uid.ristonino.client.model.types;

import java.util.HashMap;

public class Cart {
    private HashMap<String, CartInterface> listaCart = new HashMap<>();

    public void add(String id, int quantity, double price) {
        listaCart.put(id, new CartInterface(quantity, price));
    }

    public void remove(String id) {
        listaCart.remove(id);
    }
    public void update(String id, int quantity) {
        listaCart.get(id).setQuantity(quantity);
    }

    public double getTotal() {
        double total = 0.00;
        for (CartInterface cart : listaCart.values()) {
            total += cart.getQuantity() * cart.getPrice();
        }
        return total;
    }
}
