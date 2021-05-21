package com.yolotech.defapi.mappers;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.dto.category.CategoryDTOPost;
import com.yolotech.defapi.dto.category.CategoryDTOPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

  public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  public abstract Category toCategory(CategoryDTOPost categoryDTOPost);

  public abstract Category toCategory(CategoryDTOPut categoryDTOPut);
}
