package com.yolotech.defapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yolotech.defapi.domain.enums.CourseStatus;

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

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
      timezone = "GMT")
  private LocalDateTime regDate;
  // **** Testar o LocalDateTime.now() na hora de instanciar o objeto **** //

  private Integer courseStatus;

  private boolean edited;
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
      LocalDateTime regDate,
      CourseStatus courseStatus,
      boolean edited,
      boolean active) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.instructor = instructor;
    this.user = user;
    this.site = site;
    this.price = price;
    this.length = length;
    this.slug = slug;
    this.regDate = regDate;
    setCourseStatus(courseStatus);
    this.edited = edited;
    this.active = active;
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
    return CourseStatus.valueOf(courseStatus);
  }

  public void setCourseStatus(CourseStatus courseStatus) {
    if (courseStatus != null) {
      this.courseStatus = courseStatus.getCode();
    }
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
