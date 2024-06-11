package org.uid.ristonino.client.model.types;

public class Flag {
    private int flagId;
    private String flagName;
    private String flagImage;

    public Flag(int flagId, String flagName, String flagImage) {
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
    public String getFlagImage() {
        return flagImage;
    }
}
