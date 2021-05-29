package com.yolotech.defapi.mappers;

import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.dto.course.CourseDTOPost;
import com.yolotech.defapi.dto.course.CourseDTOPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    public static final CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    public abstract Course toCourse(CourseDTOPost courseDTOPost);

    public abstract Course toCourse(CourseDTOPut courseDTOPut);
}
