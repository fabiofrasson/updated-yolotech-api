package com.yolotech.defapi.dto.course;

import com.yolotech.defapi.domain.Account;
import lombok.Data;

@Data
public class CourseDTOPut {

    private Long id;
    private String name;
    private String description;
    private String instructor;
    private Account user;
    private String site;
    private Double price;
    private Double length;
    private String slug;
}
