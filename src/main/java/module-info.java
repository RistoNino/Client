module org.uid.ristonino.client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires io.vertx.core;
    requires io.github.cdimascio.dotenv.java;

    opens org.uid.ristonino.client to javafx.fxml;
    opens org.uid.ristonino.client.controller to javafx.fxml;
    exports org.uid.ristonino.client;
    exports org.uid.ristonino.client.controller;
    exports org.uid.ristonino.client.model;
    exports org.uid.ristonino.client.model.types;
    exports org.uid.ristonino.client.model.api;
}