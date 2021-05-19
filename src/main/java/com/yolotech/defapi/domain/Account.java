package com.yolotech.defapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yolotech.defapi.domain.enums.AccRole;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Account implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;
  private String title;
  private String contact;
  private String username;
  private String bio;
  private String github;
  private String linkedIn;
  private String passwd;
  private Integer accRole;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Course> courseList = new ArrayList<>();

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
      timezone = "GMT")
  private LocalDateTime regDate;
  // **** Testar o LocalDateTime.now() na hora de instanciar o objeto **** //

  private boolean active;

  public Account() {}

  public Account(
      Long id,
      String fullName,
      String title,
      String contact,
      String username,
      String bio,
      String github,
      String linkedIn,
      String passwd,
      AccRole accRole,
      LocalDateTime regDate,
      boolean active) {
    this.id = id;
    this.fullName = fullName;
    this.title = title;
    this.contact = contact;
    this.username = username;
    this.bio = bio;
    this.github = github;
    this.linkedIn = linkedIn;
    this.passwd = passwd;
    setAccRole(accRole);
    this.regDate = regDate;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public String getLinkedIn() {
    return linkedIn;
  }

  public void setLinkedIn(String linkedIn) {
    this.linkedIn = linkedIn;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public AccRole getAccRole() {
    return AccRole.valueOf(accRole);
  }

  public void setAccRole(AccRole accRole) {
    if (accRole != null) {
      this.accRole = accRole.getCode();
    }
  }

  public List<Course> getCourseList() {
    return courseList;
  }

  public LocalDateTime getRegDate() {
    return regDate;
  }

  public void setRegDate(LocalDateTime regDate) {
    this.regDate = regDate;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
