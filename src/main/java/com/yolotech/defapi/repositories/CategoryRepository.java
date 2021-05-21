package com.yolotech.defapi.repositories;

import com.yolotech.defapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
