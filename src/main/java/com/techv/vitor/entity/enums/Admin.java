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
}
