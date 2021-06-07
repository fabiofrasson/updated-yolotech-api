package com.yolotech.defapi.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.yolotech.defapi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
  STUDENT(Sets.newHashSet(COURSE_READ, REVIEW_READ, REVIEW_WRITE)),
  ADMIN(
      Sets.newHashSet(
          ACCOUNT_READ,
          ACCOUNT_WRITE,
          COURSE_READ,
          COURSE_WRITE,
          CATEGORY_READ,
          CATEGORY_WRITE,
          REVIEW_READ,
          REVIEW_WRITE)),
  COMPANYADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, CATEGORY_READ, REVIEW_READ));

  private final Set<ApplicationUserPermission> perssions;

  ApplicationUserRole(Set<ApplicationUserPermission> perssions) {
    this.perssions = perssions;
  }

  public Set<ApplicationUserPermission> getPerssions() {
    return perssions;
  }
}
