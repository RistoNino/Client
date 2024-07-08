package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.CheckCategories;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.model.types.Flag;
import org.uid.ristonino.client.model.types.Item;

import java.io.IOException;
import java.util.List;

public class CategoryController {
    @FXML private Label categoryLabel;
    @FXML private VBox categoryContainer;

    boolean notEmpty = false;

    public void initialize(String category) {
        List<Item> itemList = CheckCategories.instance.getItemList();
        categoryLabel.setText(category);
        for (Item item : itemList) {
            if (item.getCategory().equals(category)) {
                notEmpty = true;
                loadItem(item.getId(), item.getName(), item.getDescription(), item.getIngredients(), item.getPrice(), item.getFlags(), item.getImage());
            }
        }
    }

    private void loadItem(int id, String name, String description, List<String> ingredients, Double price, List<Flag> flags, Image itemImage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Settings.SCENE_PATH + "view/item.fxml"));
            Node node = fxmlLoader.load();
            MenuItemController controller = fxmlLoader.getController();
            controller.initialize(id, name, description, ingredients, price, flags, itemImage);
            categoryContainer.getChildren().add(node);
        } catch (IOException ignored) {
        }
    }

    public boolean isEmpty() {
        return !notEmpty;
    }
}
