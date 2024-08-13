package com.techv.vitor.entity.enums;

public enum Admin {

    ADMIN(1),
    NOT_ADMIN(0);

    private final int adminValue;

    Admin(int adminValue) {
        this.adminValue = adminValue;
    }

    public int getAdminValue() {
        return adminValue;
    }

    public static Admin valueOf(int adminValue) {
        for(Admin a : Admin.values()) {
            if (a.getAdminValue() == adminValue) {
                return a;
            }
        }
        throw new IllegalArgumentException("Please set a valid admin status...");
    }
}
