package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.dto.course.CourseDTOPost;
import com.yolotech.defapi.dto.course.CourseDTOPut;
import com.yolotech.defapi.exceptions.BadRequestException;
import com.yolotech.defapi.mappers.CourseMapper;
import com.yolotech.defapi.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> listAll() { return courseRepository.findAll(); }

    public Course findByIdOrThrowBadRequestException(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new BadRequestException("Course not found. Please try again."));
    }

    @Transactional
    public Course save(CourseDTOPost courseDTOPost) {
        return courseRepository.save(CourseMapper.INSTANCE.toCourse(courseDTOPost));
    }

    public void delete(Long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public void replace(CourseDTOPut courseDTOPut) {
        Course savedCourse = findByIdOrThrowBadRequestException(courseDTOPut.getId());
        Course course = CourseMapper.INSTANCE.toCourse(courseDTOPut);
        course.setId(savedCourse.getId());
        courseRepository.save(course);
    }
}
