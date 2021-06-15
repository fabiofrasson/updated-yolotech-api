package com.yolotech.defapi.security;

public enum ApplicationUserPermission {
    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
//    COURSE_CATEGORY_WRITE("course-category:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    REVIEW_READ("review:read"),
    REVIEW_WRITE("review:write");

    private final String permission;

    ApplicationUserPermission(String persmission) {
        this.permission = persmission;
    }

    public String getPermission() {
        return permission;
    }
}
