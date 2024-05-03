package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.types.Item;

import java.io.IOException;
import java.util.List;

public class HomePageController {
    @FXML
    private ListView<Node> itemsList;

    @FXML
    private void initialize() {
        for (Item item : Debug.items) {
            loadItem(item.getName(), item.getCategory(), item.getDescription(), item.getIngredients(), item.getPrice());
        }
    }

    private void loadItem(String name, String category, String description, List<String> ingredients, Double price) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Settings.SCENE_PATH + "view/menu-item.fxml"));
            Node node = fxmlLoader.load();
            MenuItemController controller = fxmlLoader.getController();
            controller.initialize(name, category, description, ingredients, price);
            itemsList.getItems().add(node);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
