package org.uid.ristonino.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.view.SceneHandler;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneHandler.getInstance().init(primaryStage);
    }

    @Override
    public void stop() {
        ApiHandler.getInstance().closeConnection();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
