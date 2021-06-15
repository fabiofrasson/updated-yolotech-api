package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.dto.category.CategoryDTOPost;
import com.yolotech.defapi.dto.category.CategoryDTOPut;
import com.yolotech.defapi.exceptions.BadRequestException;
import com.yolotech.defapi.mappers.CategoryMapper;
import com.yolotech.defapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<Category> listAll() {
    return categoryRepository.findAll();
  }

  public Category findByIdOrThrowBadRequestException(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new BadRequestException("Category not found. Please try again."));
  }

  @Transactional
  public Category save(CategoryDTOPost categoryDTOPost) {
    Category findCat = categoryRepository.findCategoryByNameIgnoreCase(categoryDTOPost.getName());
    if (findCat != null) {
      throw new BadRequestException("Category " + categoryDTOPost.getName() + " already exists.");
    } else {
      return categoryRepository.save(CategoryMapper.INSTANCE.toCategory(categoryDTOPost));
    }
  }

  public void delete(Long id) {
    categoryRepository.delete(findByIdOrThrowBadRequestException(id));
  }

  public void replace(CategoryDTOPut categoryDTOPut) {
    Category savedCategory = findByIdOrThrowBadRequestException(categoryDTOPut.getId());
    Category category = CategoryMapper.INSTANCE.toCategory(categoryDTOPut);
    category.setId(savedCategory.getId());
    categoryRepository.save(category);
  }
}
