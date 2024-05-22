package org.uid.ristonino.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.Settings;

public class SceneHandler {
    private Stage stage;
    private Scene scene;

    // Path
    private final static String CSS_PATH = Settings.SCENE_PATH + "css/";
    private final static String VIEW_PATH = Settings.SCENE_PATH + "view/";

    // Risoluzione
    private final static double minWidth = 1200;
    private final static double minHeight = 600;

    private static final SceneHandler instance = new SceneHandler();
    private String theme = "light";

    private final ColorAdjust protanopia = new ColorAdjust(-0.2, -0.3, 0.1, 0.0);
    private final ColorAdjust deuteranopia = new ColorAdjust(-0.1, -0.2, 0.1, 0.0);
    private final ColorAdjust tritanopia = new ColorAdjust(0.2, -0.3, 0.1, 0.0);
    private final ColorAdjust normal = new ColorAdjust(0.0, 0.0, 0.0, 0.0);

    private final String daltonismo = "normal";

    public static SceneHandler getInstance() {
        return instance;
    }

    private SceneHandler() {
    }

    private void applyTheme() {
        this.scene.getStylesheets().clear();
        this.scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH + theme + "/main.css")).toExternalForm());
        this.scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH + "style.css")).toExternalForm());

        switch (daltonismo) {
            case "protanopia":
                scene.getRoot().setEffect(protanopia);
                break;
            case "deuteranopia":
                scene.getRoot().setEffect(deuteranopia);
                break;
            case "tritanopia":
                scene.getRoot().setEffect(tritanopia);
                break;
            case null, default:
                scene.getRoot().setEffect(normal);
                break;
        }

    }

    public void changeTheme(String newTheme) {
        if (newTheme.equals(this.theme)) {
            return;
        }
        try {
            Objects.requireNonNull(getClass().getResource(CSS_PATH + theme + "/main.css"));
            this.theme = newTheme;
        } finally {
            applyTheme();
        }
    }

    public void init(Stage stage) throws IOException {
        if (this.stage == null) {
            this.stage = stage;
            if (Debug.IS_ACTIVE) {
                this.stage.setWidth(Debug.width);
                this.stage.setHeight(Debug.height);
            } else {
                this.stage.setFullScreen(true);
            }
            this.stage.setTitle("RistoNino");
            createLoginScene();
            this.stage.setScene(scene);
            this.stage.show();
        }
    }

    private void setResolution() {
        this.stage.setMinWidth(minWidth);
        this.stage.setMinHeight(minHeight);
        if (Debug.IS_ACTIVE) {
            this.stage.setHeight(Debug.height);
            this.stage.setWidth(Debug.width);
        } else {
            this.stage.setFullScreen(true);
        }
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.setResizable(false);
        applyTheme();
    }

    private <T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceName)));
        return fxmlLoader.load();
    }

    public void createHomeScene() {
        try {
            scene.setRoot(loadRootFromFXML(VIEW_PATH + "menu-page.fxml"));
            setResolution();
        } catch (IOException ignored) {
            Debug.print(ignored.toString());
        }
    }

    public void createLoginScene() {
        try {
            if (scene == null) {
                scene = new Scene(loadRootFromFXML(VIEW_PATH + "login-page.fxml"));
            } else {
                scene.setRoot(loadRootFromFXML(VIEW_PATH + "login-page.fxml"));
            }

        } catch (IOException ignored) {
        } finally {
            setResolution();
        }
    }
}

