package org.uid.ristonino.client.model;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Image64Decoder {

    public static Image decodeToJavaFXImage(String base64String) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
                return new Image(bis);
            }
        } catch (IOException ignored) {
        }
        return null;
    }
}
