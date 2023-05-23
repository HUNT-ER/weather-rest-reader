package com.boldyrev.sensorrestreader.enums;

import java.util.Arrays;

public enum SensorType {
    WEATHER("Weather");

    private final String type;

    SensorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean isExisted(String type) {
        boolean isExisted = true;

        try {
            SensorType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            isExisted = false;
        }
        return isExisted;
    }
}
