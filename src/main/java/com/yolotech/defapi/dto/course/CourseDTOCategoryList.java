package com.yolotech.defapi.dto.course;

import com.yolotech.defapi.domain.Category;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTOCategoryList {

    private Long id;
    private List<Category> categoryList;
}
