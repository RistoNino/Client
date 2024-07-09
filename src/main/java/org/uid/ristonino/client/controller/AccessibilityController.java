package org.uid.ristonino.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveModal;
import org.uid.ristonino.client.view.SceneHandler;

public class AccessibilityController {
    @FXML private HBox fontButtons;
    @FXML private HBox daltonismoButtons;
    @FXML private StackPane accessibilityModalContainer;

    @FXML
    public void initialize() {
        String fontSize = SceneHandler.getInstance().getFont();
        String daltonismo = SceneHandler.getInstance().getDaltonismo();
        changeActiveButtonFont(fontSize);
        changeActiveButtonDaltonismo(daltonismo);

        accessibilityModalContainer.setOnMouseClicked(event -> {
            Node node = (Node) event.getTarget();
            if (node.getId() != null && node.getId().equals("accessibilityModalContainer")) {
                closeModal();
            }
        });

    }

    private void changeActiveButtonFont(String font) {
        for (Node node : fontButtons.getChildren()) {
            Button button = (Button) node;

            if (button.getStyleClass().contains("activeButton") && !button.getId().equals(font)) {
                button.getStyleClass().remove("activeButton");
            }

            if (button.getId().equals(font) && !button.getStyleClass().contains("activeButton")) {
                button.getStyleClass().add("activeButton");
            }
        }
    }

    private void changeActiveButtonDaltonismo(String daltonismo) {
        for (Node node : daltonismoButtons.getChildren()) {
            Button button = (Button) node;

            if (button.getStyleClass().contains("activeButton") && !button.getId().equals(daltonismo)) {
                button.getStyleClass().remove("activeButton");
            }

            if (button.getId().equals(daltonismo) && !button.getStyleClass().contains("activeButton")) {
                button.getStyleClass().add("activeButton");
            }
        }
    }

    public void closeModal() {
        EventBus.getInstance().fireEvent(new RemoveModal());
    }

    @FXML
    public void changeFontSize(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String fontSize = button.getId();
        changeActiveButtonFont(fontSize);
        SceneHandler.getInstance().changeFontSize(fontSize);
    }

    @FXML
    private void changeDaltonismo(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String daltonismo = button.getId();
        changeActiveButtonDaltonismo(daltonismo);
        SceneHandler.getInstance().changeDaltonismo(daltonismo);
    }
}
