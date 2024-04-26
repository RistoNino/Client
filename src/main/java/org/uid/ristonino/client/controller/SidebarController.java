package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SidebarController {
    @FXML private VBox sidebar;
    @FXML private Button home;
    @FXML private Button antipasti;
    @FXML private Button primi;
    @FXML private Button secondi;

    private void changeCurrent(Button button) {
        home.getStyleClass().remove("activeSidebar");
        antipasti.getStyleClass().remove("activeSidebar");
        primi.getStyleClass().remove("activeSidebar");
        secondi.getStyleClass().remove("activeSidebar");
        button.getStyleClass().add("activeSidebar");
    }

    @FXML
    private void initialize() {
        home.getStyleClass().add("activeSidebar");
    }

    @FXML
    private void vaiHome() {
        changeCurrent(home);
    }

    @FXML
    private void vaiAntipasti() {
        changeCurrent(antipasti);
    }

    @FXML
    private void vaiPrimi() {
        changeCurrent(primi);
    }

    @FXML
    private void vaiSecondi() {
        changeCurrent(secondi);
    }
}
