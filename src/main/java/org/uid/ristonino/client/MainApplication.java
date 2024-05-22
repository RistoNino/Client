package org.uid.ristonino.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.view.SceneHandler;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.getInstance().init(primaryStage);
    }

    @Override
    public void init() throws Exception {
        super.init();
        ApiHandler.getInstance();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
