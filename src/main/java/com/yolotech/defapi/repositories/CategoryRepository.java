package com.yolotech.defapi.repositories;

import com.yolotech.defapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByNameIgnoreCase(String name);
}
