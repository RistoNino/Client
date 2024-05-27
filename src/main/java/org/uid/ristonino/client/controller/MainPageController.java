package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.CreateCustomItem;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveModal;

import java.io.IOException;
import java.util.List;

public class MainPageController {
    @FXML
    private StackPane containerStack;

    private Node customItemModal;

    @FXML
    public void initialize() {
        EventBus.getInstance().addEventHandler(CreateCustomItem.EVENT_TYPE, event -> {
            String customName = ((CreateCustomItem) event).getName();
            String customDesc = ((CreateCustomItem) event).getDescription();
            List<String> customIngs = ((CreateCustomItem) event).getIngredients();
            double customPrice = ((CreateCustomItem) event).getPrice();

            if (createModal(customName, customDesc, customIngs, customPrice)) {
                containerStack.getChildren().add(customItemModal);
                customItemModal.setVisible(true);
            }

        });

        // Evento mandato dal modal per dire che deve eliminare il modal
        EventBus.getInstance().addEventHandler(RemoveModal.EVENT_TYPE, event -> {
            customItemModal.setVisible(false);
            containerStack.getChildren().removeLast();
            customItemModal = null;
        });
    }

    private boolean createModal(String name, String desc, List<String> ings, double prc) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((Settings.SCENE_PATH + "view/custom-item.fxml")));
            customItemModal = fxmlLoader.load();
            CustomItemController customItemController = fxmlLoader.getController();
            customItemController.initialize(name, desc, ings, prc);
            return true;
        } catch (IOException ignored) {

        }
        return false;
    }
}
