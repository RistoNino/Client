package org.uid.ristonino.client.model;

import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.model.types.Item;

import java.util.*;

public class CheckCategories {
    public HashMap<Integer, String> categories = ApiHandler.getInstance().getCategories();
    public  List<Item> itemList = ApiHandler.getInstance().getItems();
    public static final CheckCategories instance = new CheckCategories();

    private CheckCategories() {

    }

    public List<Item> getItemList() {
        return itemList;
    }
    public HashMap<Integer, String> getCategories() {
        return categories;
    }
    public void setCategories(HashMap<Integer, String> categories) {
        this.categories = categories;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public HashMap<Integer, String> getFilledCategories() {
        HashMap<Integer, String> filledCategories = new HashMap<>();
        for (Integer categoryId : categories.keySet())
            for (Item item : itemList) {
                String category = categories.get(categoryId);
                if (item.getCategory().equals(category)) {
                    filledCategories.put(categoryId, category);
                }
            }

        return filledCategories;
    }
}
