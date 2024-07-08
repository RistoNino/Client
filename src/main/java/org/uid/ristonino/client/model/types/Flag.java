package org.uid.ristonino.client.model.types;

import javafx.scene.image.Image;

public class Flag {
    private final int flagId;
    private final String flagName;
    private Image flagImage;

    public Flag(int flagId, String flagName, Image flagImage) {
        this.flagId = flagId;
        this.flagName = flagName;
        this.flagImage = flagImage;
    }

    public int getFlagId() {
        return flagId;
    }

    public String getFlagName() {
        return flagName;
    }

    public Image getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(Image flagImage) {
        this.flagImage = flagImage;
    }
}


