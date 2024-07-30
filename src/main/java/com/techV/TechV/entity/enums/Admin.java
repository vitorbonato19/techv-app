package com.techV.TechV.entity.enums;

public enum Admin {

    ADMIN(1),
    NOT_ADMIN(0);

    private final int adminNumber;

    Admin(int adminNumber) {
        this.adminNumber = adminNumber;
    }

    public int getAdminNumber() {
        return adminNumber;
    }
}
