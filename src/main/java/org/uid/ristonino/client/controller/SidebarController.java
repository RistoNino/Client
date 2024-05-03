package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.events.SelectedCategory;

import java.util.ArrayList;
import java.util.List;

public class SidebarController {
    @FXML private VBox sidebar;
    @FXML private Button home;
    private final List<Button> sidebarButtons = new ArrayList<>();

    private void changeCurrent(Button button) {
        for (Button b : sidebarButtons) {
            b.getStyleClass().remove("activeSidebar");
        }
        button.getStyleClass().add("activeSidebar");
    }

    @FXML
    private void initialize() {
        home.getStyleClass().add("activeSidebar");
        sidebarButtons.add(home);
        for (String category : Debug.categoryList) {
            addCategory(category);
        }
    }

    @FXML
    private void vaiHome() {
        changeCurrent(home);
    }

    private void addCategory(String name) {
        Button button = new Button(name);
        SelectedCategory category = new SelectedCategory(name);
        button.setOnAction(event -> {
            changeCurrent(button);
            button.fireEvent(category);
        });
        sidebarButtons.add(button);
        sidebar.getChildren().add(button);

    }

}
