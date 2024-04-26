package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.uid.ristonino.client.view.SceneHandler;

public class LoginPageController {
    public boolean isDarkMode = true;
    @FXML
    private Button Pulsante;

    @FXML
    private Label prova;

    @FXML
    private void cambiaTesto() {
        if (isDarkMode) {
            SceneHandler.getInstance().changeTheme("light");
            prova.setText("TEST RIUSCITO - Changed theme to light");
            this.isDarkMode = false;
        } else {
            SceneHandler.getInstance().changeTheme("dark");
            prova.setText("TEST RIUSCITO - Changed theme to dark");
            this.isDarkMode = true;
        }
    }

    @FXML
    private void vaiHome() {
        SceneHandler.getInstance().changeTheme("dark");
        SceneHandler.getInstance().createHomeScene();
    }
}
