package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kordamp.ikonli.javafx.FontIcon;
import org.uid.ristonino.client.view.SceneHandler;

public class LoginPageController {
    @FXML Label numCoperti;
    @FXML Button buttonRemCop;
    @FXML Button buttonAddCop;

    private int maxNumeroCoperti = 12;

    private int numeroCoperti = 1;

    private FontIcon fontIconRemove = new FontIcon("mdrmz-person_remove_alt_1");
    private FontIcon fontIconAdd = new FontIcon("mdrmz-person_add_alt_1");
    private FontIcon people = new FontIcon("mdsmz-people");

    @FXML
    private void initialize() {
        fontIconRemove.setIconSize(30);
        fontIconAdd.setIconSize(30);
        people.setIconSize(30);
        buttonRemCop.setGraphic(fontIconRemove);
        buttonAddCop.setGraphic(fontIconAdd);
        numCoperti.setGraphic(people);
    }

    @FXML
    private void addCoperto() {
        if (numeroCoperti < maxNumeroCoperti) {
            numeroCoperti++;
            numCoperti.setText(String.valueOf(numeroCoperti));
            buttonRemCop.setDisable(false);
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
        }
    }

    @FXML
    private void vaiHome() {
        SceneHandler.getInstance().changeTheme("light");
        SceneHandler.getInstance().createHomeScene();
    }
}
