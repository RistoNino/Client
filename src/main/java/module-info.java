module org.uid.ristonino.client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.json;
    requires io.vertx.core;

    opens org.uid.ristonino.client to javafx.fxml;
    opens org.uid.ristonino.client.controller to javafx.fxml;
    exports org.uid.ristonino.client;
    exports org.uid.ristonino.client.controller;
    exports org.uid.ristonino.client.model;
    exports org.uid.ristonino.client.model.types;
}