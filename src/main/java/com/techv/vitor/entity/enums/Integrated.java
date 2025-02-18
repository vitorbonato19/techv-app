package com.techv.vitor.entity.enums;

public enum Integrated {

    TRUE(1),
    FALSE(0);

    private final int value;

    Integrated(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Integrated valueOf(int value) {
        for(Integrated i : Integrated.values()) {
            if (i.getValue() == value) {
                return i;
            }
        }
        throw new IllegalArgumentException("Please set a valid integrated value...");
    }
}
