package com.yolotech.defapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yolotech.defapi.domain.enums.AccRole;
import org.hibernate.annotations.CreationTimestamp;

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

  @Enumerated(EnumType.STRING)
  private AccRole accRole = AccRole.STUDENT;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Course> courseList = new ArrayList<>();

  @CreationTimestamp private LocalDateTime regDate;

  @Column(
      name = "active",
      insertable = false,
      nullable = false,
      columnDefinition = "boolean default true")
  private boolean active;

  public Account() {}

  public Account(
      String fullName,
      String title,
      String contact,
      String username,
      String bio,
      String github,
      String linkedIn,
      String passwd) {
    this.fullName = fullName;
    this.title = title;
    this.contact = contact;
    this.username = username;
    this.bio = bio;
    this.github = github;
    this.linkedIn = linkedIn;
    this.passwd = passwd;
  }

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
      AccRole accRole) {
    this.id = id;
    this.fullName = fullName;
    this.title = title;
    this.contact = contact;
    this.username = username;
    this.bio = bio;
    this.github = github;
    this.linkedIn = linkedIn;
    this.passwd = passwd;
    this.accRole = accRole;
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
    return accRole;
  }

  public void setAccRole(AccRole accRole) {
    this.accRole = accRole;
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
