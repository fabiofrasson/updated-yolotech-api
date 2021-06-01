package com.yolotech.defapi.resources;

import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.dto.course.CourseDTOPost;
import com.yolotech.defapi.dto.course.CourseDTOPut;
import com.yolotech.defapi.services.CourseService;
import com.yolotech.defapi.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("courses")
@Log4j2
@RequiredArgsConstructor
public class CourseResource {
  private final DateUtil dateUtil;
  private final CourseService courseService;

  @GetMapping
  @ApiOperation(value = "Return a list with all Courses", response = Course.class)
  public ResponseEntity<List<Course>> list() {
    log.info(dateUtil.formatLocalDateTimetoDatabaseStyle(LocalDateTime.now()));
    return ResponseEntity.ok(courseService.listAll());
  }

  @GetMapping(path = "/{id}")
  @ApiOperation(value = "Perform a search by Id within Courses List", response = Course.class)
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return ResponseEntity.ok(courseService.findByIdOrThrowBadRequestException(id));
  }

  @PostMapping
  @ApiOperation(value = "Add a Course to Courses List")
  public ResponseEntity<Course> save(@RequestBody @Valid CourseDTOPost courseDTOPost) {
    return new ResponseEntity<>(courseService.save(courseDTOPost), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  @ApiOperation(value = "Course deletion by Id")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    courseService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody CourseDTOPut courseDTOPut) {
    courseService.replace(courseDTOPut);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
