package org.uid.ristonino.client.model.types;

import java.util.List;

public class Order {
    private String itemName;
    private double price;
    private String removedIngredients;
    private String notes;
    private int quantity;

    public Order(String itemName, int quantity, double price, List<String> removedIngredients, String notes) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.removedIngredients = createIngString(removedIngredients);
        this.notes = notes;
    }

    private String createIngString(List<String> ingredients) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            stringBuilder.append(ingredients.get(i));
            if (i != ingredients.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRemovedIngredients() {
        if (!removedIngredients.isEmpty()) {
            return "NO - " + removedIngredients;
        }
        return removedIngredients;
    }
    public String getNotes() {
        return notes;
    }
    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setRemovedIngredients(List<String> removedIngredients) {
        this.removedIngredients = createIngString(removedIngredients);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
