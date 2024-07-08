package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.CheckCategories;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.OpenAccessibilityModal;
import org.uid.ristonino.client.model.events.ScrolledCategory;
import org.uid.ristonino.client.model.events.SelectedCategory;

import java.util.*;

public class SidebarController {
    @FXML private VBox sidebar;

    private final List<Button> sidebarButtons = new ArrayList<>();

    private void changeCurrent(Button button) {
        for (Button b : sidebarButtons) {
            b.getStyleClass().remove("activeSidebar");
        }
        button.getStyleClass().add("activeSidebar");
    }

    @FXML
    private void initialize() {
        Set<String> categories = CheckCategories.instance.getFilledCategories();
        sidebar.setAlignment(Pos.TOP_CENTER);
        for (String category : categories) {
            addCategory(category);
        }
        EventBus.getInstance().addEventHandler(ScrolledCategory.EVENT_TYPE, event -> {
            String categoria = event.getCategoryName();
            for (Button b : sidebarButtons) {
                if (b.getText().equals(categoria)) {
                    if (!b.getStyleClass().contains("activeSidebar")) {
                        changeCurrent(b);
                    }
                    break;
                }
            }
        });
    }


    private void addCategory(String name) {
        Button button = new Button(name);
        button.setMaxWidth(Double.MAX_VALUE);
        SelectedCategory category = new SelectedCategory(name);
        button.setOnAction(event -> {
            changeCurrent(button);
            EventBus.getInstance().fireEvent(category);
        });
        if (sidebarButtons.isEmpty()) {
            button.getStyleClass().add("activeSidebar");
        }
        sidebarButtons.add(button);
        sidebar.getChildren().add(button);

    }

    @FXML
    public void openAccessibility() {
        EventBus.getInstance().fireEvent(new OpenAccessibilityModal());
    }

}
