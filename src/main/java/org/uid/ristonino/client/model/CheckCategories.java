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

    public Set<String> getFilledCategories() {
        Set<String> retCategories = new HashSet<String>();
        for (Item item : itemList) {
            retCategories.add(item.getCategory());
        }

        return retCategories;
    }
}
