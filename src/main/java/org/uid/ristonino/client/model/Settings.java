package org.uid.ristonino.client.model;

import java.util.Objects;

public class Settings {
    public static final String SCENE_PATH = "/org/uid/ristonino/client/";
    public static final String DEFAULT_IMAGE = Objects.requireNonNull(Settings.class.getResource(SCENE_PATH + "images/background-login.png")).toExternalForm();
    public static final String DEFAULT_IMAGE_FLAG = Objects.requireNonNull(Settings.class.getResource(Settings.SCENE_PATH + "images/background-login.png")).toExternalForm();

    public static final int SOCKET_PORT = 1234;
    public static final int SOCKET_TIMEOUT = 5000;
}
