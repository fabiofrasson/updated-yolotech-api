package com.yolotech.defapi.repositories;

import com.yolotech.defapi.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
