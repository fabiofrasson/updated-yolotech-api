package com.yolotech.defapi.dto.course;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.domain.Course;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CourseDTOPost {

    private String name;
    private String description;
    private String instructor;
    private Account user;
    private String site;
    private Double price;
    private Double length;
    private String slug;

    // Método padrão com ModelMapper
    public static CourseDTOPost create(Course course) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(course, CourseDTOPost.class);
    }

    // Constructor example
//    public CourseDTOPost(Course course) {
//        this.name = course.getName();
//        this.description = course.getDescription();
//        this.instructor = course.getInstructor();
//        this.user = course.getUser();
//        this.site = course.getSite();
//        this.price = course.getPrice();
//        this.length = course.getLength();
//        this.slug = course.getSlug();
//    }
}
