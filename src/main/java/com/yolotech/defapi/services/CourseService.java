package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.dto.course.CourseDTOCategoryList;
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
        course.setRegDate(savedCourse.getRegDate());
        course.setCourseStatus(savedCourse.getCourseStatus());
        course.setEdited(true);
        course.setActive(savedCourse.isActive());
        courseRepository.save(course);
    }

    public void addCategory(CourseDTOCategoryList courseDTOCategoryList) {
        Course savedCourse = findByIdOrThrowBadRequestException(courseDTOCategoryList.getId());
        Course course = CourseMapper.INSTANCE.toCourse(courseDTOCategoryList);
        course.setName(savedCourse.getName());
        course.setDescription(savedCourse.getDescription());
        course.setInstructor(savedCourse.getInstructor());
        course.setSite(savedCourse.getSite());
        course.setPrice(savedCourse.getPrice());
        course.setLength(savedCourse.getLength());
        course.setSlug(savedCourse.getSlug());
        course.setRegDate(savedCourse.getRegDate());
        course.setCourseStatus(savedCourse.getCourseStatus());
        if(!course.isEdited()) {
            course.setEdited(true);
        } else {
            course.setEdited(savedCourse.isEdited());
        }
        course.setActive(savedCourse.isActive());
        course.getCategoryList().addAll(savedCourse.getCategoryList());
        courseRepository.save(course);
    }
}
