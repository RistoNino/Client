package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.view.SceneHandler;

public class LoginPageController {
    @FXML private Label numCoperti;
    @FXML private Button buttonRemCop;
    @FXML private Button buttonAddCop;
    @FXML private Button startButton;

    private int maxNumeroCoperti = ApiHandler.getInstance().getMassimoCoperti();

    private int numeroCoperti = 1;

    @FXML
    private void initialize() {

    }

    @FXML
    private void addCoperto() {
        if (numeroCoperti < maxNumeroCoperti) {
            numeroCoperti++;
            numCoperti.setText(String.valueOf(numeroCoperti));
            buttonRemCop.setDisable(false);
            if (numeroCoperti == maxNumeroCoperti) {
                buttonAddCop.setDisable(true);
            }
        }

    }

    @FXML
    private void removeCoperto() {
        if (numeroCoperti > 1) {
            numeroCoperti--;
            numCoperti.setText(String.valueOf(numeroCoperti));
            if (numeroCoperti == 1) {
                buttonRemCop.setDisable(true);
            }
            if (buttonAddCop.isDisable() && numeroCoperti < maxNumeroCoperti) {
                buttonAddCop.setDisable(false);
            }
        }
    }

    @FXML
    private void vaiHome() {
        buttonAddCop.setDisable(true);
        buttonRemCop.setDisable(true);
        startButton.setDisable(true);
        SceneHandler.getInstance().loadHomeScene();
        ApiHandler.getInstance().accessApi(numeroCoperti);
    }
}
