package org.uid.ristonino.client.model.types;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private String category = "";
    private List<String> ingredients = new ArrayList<String>();
    private String description = "";
    private String image = "";
    private String type = "";
    private double price = 0.00;

    // CON TUTTO
    public Item(int id, String name, String category, List<String> ingredients, String description, String image, String type, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.description = description;
        this.image = image;
        this.type = type;
        this.price = price;
    }
    // SENZA TIPO
    public Item(int id, String name, String category, List<String> ingredients, String description, String image, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.description = description;
        this.image = image;
        this.price = price;
    }
    // SENZA IMMAGINE E TIPO
    public Item(int id, String name, String category, List<String> ingredients, String description,double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
