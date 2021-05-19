package com.yolotech.defapi.domain.enums;

public enum CourseStatus {
  PENDING(1),
  APPROVED(2),
  REJECTED(3);

  private int code;

  private CourseStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static CourseStatus valueOf(int code) {
    for (CourseStatus value : CourseStatus.values()) {
      if (value.getCode() == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid CourseStatus code");
  }
}
