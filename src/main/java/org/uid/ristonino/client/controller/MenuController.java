package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.Settings;

import java.io.IOException;

public class MenuController {
    @FXML
    private ScrollPane itemsListContainer;
    @FXML
    private VBox itemsList;
    @FXML
    private BorderPane container;

    @FXML
    private void initialize() {
        itemsList.setSpacing(10);
        for (String category : Debug.categoryList) {
            loadCategory(category);
        }
    }


    private void loadCategory(String category) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Settings.SCENE_PATH + "view/category.fxml"));
            Node node = fxmlLoader.load();
            CategoryController controller = fxmlLoader.getController();
            controller.initialize(category);
            itemsList.getChildren().add(node);
        } catch (IOException ignored) {

        }
    }


}
