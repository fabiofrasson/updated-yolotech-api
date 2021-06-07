package com.yolotech.defapi.resources;

import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.dto.course.CourseDTOCategoryList;
import com.yolotech.defapi.dto.course.CourseDTOPost;
import com.yolotech.defapi.dto.course.CourseDTOPut;
import com.yolotech.defapi.services.CourseService;
import com.yolotech.defapi.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("courses")
@Log4j2
@RequiredArgsConstructor
public class CourseResource {
  private final DateUtil dateUtil;
  private final CourseService courseService;

  //  @GetMapping
  //  @ApiOperation(value = "Return a list with all Courses", response = Course.class)
  //  public ResponseEntity<List<Course>> list() {
  //    log.info(dateUtil.formatLocalDateTimetoDatabaseStyle(LocalDateTime.now()));
  //    return ResponseEntity.ok(courseService.listAll());
  //  }

//  @GetMapping
//  @ApiOperation(value = "Return a list with all Courses", response = Course.class)
//  public ResponseEntity listCourses() {
//    List<CourseDTOPost> courseDTOPostList = courseService.getAll();
//    return courseDTOPostList.isEmpty()
//        ? ResponseEntity.noContent().build()
//        : ResponseEntity.ok(courseDTOPostList);
//  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANYADMIN', 'ROLE_STUDENT')")
  @ApiOperation(value = "Return a list with all Courses", response = Course.class)
  public ResponseEntity<List<Course>> list() {
    return ResponseEntity.ok(courseService.listAll());
  }

  @GetMapping(path = "/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANYADMIN', 'ROLE_STUDENT')")
  @ApiOperation(value = "Perform a search by Id within Courses List", response = Course.class)
  public ResponseEntity<Course> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(courseService.findByIdOrThrowBadRequestException(id));
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANYADMIN')")
  @ApiOperation(value = "Add a Course to Courses List")
  public ResponseEntity<Course> save(@RequestBody @Valid CourseDTOPost courseDTOPost) {
    return new ResponseEntity<>(courseService.save(courseDTOPost), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Course deletion by Id")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    courseService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANYADMIN')")
  public ResponseEntity<Void> replace(@RequestBody CourseDTOPut courseDTOPut) {
    courseService.replace(courseDTOPut);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(path = "categories")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANYADMIN')")
  public ResponseEntity<Void> addCategory(
      @RequestBody CourseDTOCategoryList courseDTOCategoryList) {
    courseService.addCategory(courseDTOCategoryList);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
