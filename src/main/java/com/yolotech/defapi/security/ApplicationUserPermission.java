package com.yolotech.defapi.security;

public enum ApplicationUserPermission {
    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    REVIEW_READ("review:read"),
    REVIEW_WRITE("review:write");

    private final String persmission;

    ApplicationUserPermission(String persmission) {
        this.persmission = persmission;
    }

    public String getPersmission() {
        return persmission;
    }
}
