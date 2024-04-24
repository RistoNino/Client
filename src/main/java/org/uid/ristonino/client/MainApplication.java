package org.uid.ristonino.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.uid.ristonino.client.view.SceneHandler;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.getInstance().init(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
