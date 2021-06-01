package com.yolotech.defapi.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class CourseReview implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "courseId")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "userId")
  private Account user;

  private Double score;
  private String reviewComment;

  @CreationTimestamp private LocalDateTime regDate;

  @Column(
      name = "edited",
      insertable = false,
      nullable = false,
      columnDefinition = "boolean default false")
  private boolean edited;

  @Column(
      name = "active",
      insertable = false,
      nullable = false,
      columnDefinition = "boolean default true")
  private boolean active;

  public CourseReview() {}

  public CourseReview(Long id, Course course, Account user, Double score, String reviewComment) {
    this.id = id;
    this.course = course;
    this.user = user;
    this.score = score;
    this.reviewComment = reviewComment;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Account getUser() {
    return user;
  }

  public void setUser(Account user) {
    this.user = user;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getReviewComment() {
    return reviewComment;
  }

  public void setReviewComment(String reviewComment) {
    this.reviewComment = reviewComment;
  }

  public LocalDateTime getRegDate() {
    return regDate;
  }

  public void setRegDate(LocalDateTime regDate) {
    this.regDate = regDate;
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
