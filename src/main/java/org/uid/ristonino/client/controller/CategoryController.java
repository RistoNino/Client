package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.types.Item;

import java.io.IOException;
import java.util.List;

public class CategoryController {
    @FXML private Label categoryLabel;
    @FXML private VBox categoryContainer;

    public void initialize(String category) {
        categoryLabel.setText(category);
        for (Item item : Debug.items) {
            if (item.getCategory().equals(category)) {
                loadItem(item.getId(), item.getName(), item.getDescription(), item.getIngredients(), item.getPrice());
            }
        }
    }

    private void loadItem(int id, String name, String description, List<String> ingredients, Double price) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Settings.SCENE_PATH + "view/item.fxml"));
            Node node = fxmlLoader.load();
            MenuItemController controller = fxmlLoader.getController();
            controller.initialize(id, name, description, ingredients, price);
            categoryContainer.getChildren().add(node);
        } catch (IOException ignored) {
        }
    }
}
