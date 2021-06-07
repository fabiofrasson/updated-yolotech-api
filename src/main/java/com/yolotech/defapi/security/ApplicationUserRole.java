package com.yolotech.defapi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.yolotech.defapi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
  STUDENT(Sets.newHashSet(COURSE_READ, REVIEW_READ, REVIEW_WRITE)),
  ADMIN(
      Sets.newHashSet(
          ACCOUNT_READ,
          ACCOUNT_WRITE,
          COURSE_READ,
          COURSE_WRITE,
//          COURSE_CATEGORY_WRITE,
          CATEGORY_READ,
          CATEGORY_WRITE,
          REVIEW_READ,
          REVIEW_WRITE)),
  COMPANYADMIN(
      Sets.newHashSet(
          COURSE_READ, COURSE_WRITE, CATEGORY_READ, REVIEW_READ));

  private final Set<ApplicationUserPermission> perssions;

  ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
    this.perssions = permissions;
  }

  public Set<ApplicationUserPermission> getPermissions() {
    return perssions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions =
        getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
