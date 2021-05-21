package com.yolotech.defapi.dto.category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryDTOPost {

  @NotEmpty(message = "Category name cannot be empty.")
  private String name;
}
