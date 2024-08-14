package com.techv.vitor.entity.enums;

public enum Finished {

    TRUE(1),
    FALSE(0);

    private final int value;

    Finished(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Finished valueOf(int value) {
        for (Finished f : Finished.values()) {
            if (f.value == value) {
                return f;
            }
        }
        throw new IllegalArgumentException("Please set a valid finished value...");
    }
}
