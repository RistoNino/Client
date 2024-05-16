package org.uid.ristonino.client.model.types;

import java.util.List;
import java.util.StringJoiner;

public class Order {
    private int tableId;
    private String itemName;
    private String removedIngredients;
    private String addedIngredients;
    private int quantity;

    public Order(int tableId, String itemName, int quantity, List<String> removedIngredients, List<String> addedIngredients) {
        this.tableId = tableId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.removedIngredients = createIngString(removedIngredients);
        this.addedIngredients = createIngString(addedIngredients);
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

}
