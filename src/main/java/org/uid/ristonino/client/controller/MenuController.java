package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.CheckCategories;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuController {
    @FXML private ScrollPane itemsListContainer;
    @FXML private VBox itemsList;

    private final Map<String, Node> categorie = new HashMap<>();


    @FXML
    private void initialize() {
        HashMap<Integer, String> categories = CheckCategories.instance.getCategories();
        boolean firstCategory = true;

        for (String category : categories.values()) {
            if (firstCategory) {
                firstCategory = false;
            }
            loadCategory(category);
        }

        EventBus.getInstance().addEventHandler(SelectedCategory.EVENT_TYPE, event -> {
            String categoria = event.getCategoryName();
            VBox targetNode = (VBox) categorie.get(categoria);
            // Scorrere verso il nodo desiderato
            if (targetNode != null) {
                // Converti le coordinate del targetNode allo spazio di coordinate del VBox contenente gli elementi
                Bounds targetBounds = targetNode.localToScene(targetNode.getBoundsInLocal());
                Bounds containerBounds = itemsList.localToScene(itemsList.getBoundsInLocal());

                // Calcola la distanza dall'inizio del VBox contenente gli elementi
                double yOffset = targetBounds.getMinY() - containerBounds.getMinY();

                // Calcola la proporzione di questa distanza rispetto all'altezza totale del VBox
                double scrollPosition = yOffset / (itemsList.getHeight() - itemsListContainer.getViewportBounds().getHeight());

                // Imposta il valore di scorrimento dello ScrollPane
                itemsListContainer.setVvalue(scrollPosition + 0.001);
            }
        });
        // Listener per monitorare i cambiamenti nella scrollBar
        itemsListContainer.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            Bounds viewportBounds = itemsListContainer.getViewportBounds();
            Bounds contentBounds = itemsList.getBoundsInParent();

            // Calcola la posizione di scorrimento
            double viewportHeight = viewportBounds.getHeight();
            double scrollTop = newValue.doubleValue() * (contentBounds.getHeight() - viewportHeight);

            // Aggiungi il padding superiore del contenitore principale
            Insets mainContentPadding = itemsList.getPadding();
            scrollTop -= mainContentPadding.getTop();

            // Trova quale VBox è visibile
            for (int i = 0; i < itemsList.getChildren().size(); i++) {
                VBox categoryBox = (VBox) itemsList.getChildren().get(i);
                Label categoryLabel = (Label) categoryBox.getChildren().getFirst();
                Bounds categoryBounds = categoryBox.localToParent(categoryBox.getBoundsInParent());

                // Aggiungi il padding della VBox della categoria
                Insets categoryPadding = categoryBox.getPadding();
                double adjustedMinY = categoryBounds.getMinY() - categoryPadding.getTop();
                double adjustedMaxY = categoryBounds.getMaxY() + categoryPadding.getBottom();

                // Calcola la posizione nel contenitore rispetto al viewport
                double relativeTop = categoryBox.getBoundsInParent().getMinY() - itemsListContainer.getContent().getBoundsInParent().getMinY();
                double relativeBottom = categoryBox.getBoundsInParent().getMaxY() - itemsListContainer.getContent().getBoundsInParent().getMinY();

                // Verifica se l'elemento è visibile nel viewport
                if (relativeTop < scrollTop + viewportHeight && relativeBottom > scrollTop) {
                    EventBus.getInstance().fireEvent(new ScrolledCategory(categoryLabel.getText()));
                    break;
                }
            }
        });
    }


    private void loadCategory(String category) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Settings.SCENE_PATH + "view/category.fxml"));
            Node node = fxmlLoader.load();
            CategoryController controller = fxmlLoader.getController();
            controller.initialize(category);
            if (!controller.isEmpty()) {
                categorie.put(category, node);
                itemsList.getChildren().add(node);
            }
        } catch (IOException ignored) {

        }
    }

}
