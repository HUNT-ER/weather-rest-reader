package com.boldyrev.sensorrestreader.enums;

public enum SensorType {
    WEATHER("Weather");

    private final String type;

    SensorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
