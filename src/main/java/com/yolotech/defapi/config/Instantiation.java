package com.yolotech.defapi.config;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {

    categoryRepository.deleteAll();
    Category category = new Category(null, "Front-End", true);
    Category category1 = new Category(null, "Back-End", true);
    Category category2 = new Category(null, "DevOps", true);
    Category category3 = new Category(null, "Machine Learning", true);
    Category category4 = new Category(null, "Cybersecurity", true);
    Category category5 = new Category(null, "Database Administration", true);
    Category category6 = new Category(null, "CD/CI", true);

    categoryRepository.saveAll(
        Arrays.asList(category, category1, category2, category3, category4, category5, category6));
  }
}
