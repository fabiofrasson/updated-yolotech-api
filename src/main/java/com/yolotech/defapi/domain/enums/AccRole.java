package com.yolotech.defapi.domain.enums;

public enum AccRole {
    ADMIN(1),
    COMPANYADMIN(2),
    STUDENT(3);

    private int code;

    private AccRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AccRole valueOf(int code) {
        for (AccRole value : AccRole.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Account Role code");
    }
}
