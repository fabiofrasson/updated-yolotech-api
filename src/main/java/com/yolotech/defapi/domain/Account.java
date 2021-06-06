package com.yolotech.defapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  //  @Enumerated(EnumType.STRING)
  //  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  //  private AccRole accRole = AccRole.STUDENT;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Course> courseList = new ArrayList<>();

  @CreationTimestamp
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime regDate;

  @Column(
      name = "active",
      insertable = false,
      nullable = false,
      columnDefinition = "boolean default true")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles;

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

  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
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

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
