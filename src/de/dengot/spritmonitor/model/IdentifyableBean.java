package de.dengot.spritmonitor.model;

import java.io.Serializable;

public class IdentifyableBean implements Identifyable, Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
