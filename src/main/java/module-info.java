module org.uid.ristonino.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens org.uid.ristonino.client to javafx.fxml;
    exports org.uid.ristonino.client;
}