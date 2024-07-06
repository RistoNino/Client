package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.image.Image;

import java.util.List;

public class CreateCustomItem extends Event {
    public static final EventType<CreateCustomItem> EVENT_TYPE = new EventType<>(Event.ANY, "CREATE_CUSTOM_ITEM");

    private final int id;
    private final String name;
    private final String description;
    private final List<String> ingredients;
    private final double price;
    private final Image image;


    public CreateCustomItem(int id, String name, String description, List<String> ingredients, double price, Image image) {
        super(EVENT_TYPE);
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
    public double getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public Image getImage() { return image; }
}
