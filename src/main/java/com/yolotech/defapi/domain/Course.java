package com.yolotech.defapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yolotech.defapi.domain.enums.CourseStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String instructor;

  @ManyToMany
  @JoinTable(
      name = "course_category",
      joinColumns = @JoinColumn(name = "courseId"),
      inverseJoinColumns = @JoinColumn(name = "categoryId"))
  private List<Category> categoryList = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonIgnore
  // @JoinColumn -> name: representa o nome da chave estrangeira que identifica a classe Account
  private Account user;

  private String site;
  private Double price;
  private Double length;
  private String slug;

  @CreationTimestamp
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime regDate;

  @Enumerated(EnumType.STRING)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private CourseStatus courseStatus;

  @Column(
      name = "edited",
      nullable = false,
      insertable = false,
      columnDefinition = "boolean default false")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean edited;

  @Column(
      name = "active",
      insertable = false,
      nullable = false,
      columnDefinition = "boolean default true")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean active;

  public Course() {}

  public Course(
      Long id,
      String name,
      String description,
      String instructor,
      com.yolotech.defapi.domain.Account user,
      String site,
      Double price,
      Double length,
      String slug,
      CourseStatus courseStatus) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.instructor = instructor;
    this.user = user;
    this.site = site;
    this.price = price;
    this.length = length;
    this.slug = slug;
    this.courseStatus = courseStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInstructor() {
    return instructor;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public com.yolotech.defapi.domain.Account getUser() {
    return user;
  }

  public void setUser(Account user) {
    this.user = user;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public LocalDateTime getRegDate() {
    return regDate;
  }

  public void setRegDate(LocalDateTime regDate) {
    this.regDate = regDate;
  }

  public CourseStatus getCourseStatus() {
    return courseStatus;
  }

  public void setCourseStatus(CourseStatus courseStatus) {
    this.courseStatus = courseStatus;
  }

  public boolean isEdited() {
    return edited;
  }

  public void setEdited(boolean edited) {
    this.edited = edited;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
