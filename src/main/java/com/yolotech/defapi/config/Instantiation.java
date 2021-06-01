package com.yolotech.defapi.config;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.domain.Course;
import com.yolotech.defapi.domain.enums.AccRole;
import com.yolotech.defapi.domain.enums.CourseStatus;
import com.yolotech.defapi.repositories.AccountRepository;
import com.yolotech.defapi.repositories.CategoryRepository;
import com.yolotech.defapi.repositories.CourseRepository;
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

  private final AccountRepository accountRepository;

  private final CourseRepository courseRepository;

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

    accountRepository.deleteAll();
    Account account =
        new Account(
            null,
            "Fabio Frasson",
            "Estudante de Java",
            "fabio.frass@gmail.com",
            "fabiofrasson",
            "Bio test",
            "https://github.com/fabiofrasson",
            "https://www.linkedin.com/in/fabiofrasson/",
            "123456",
            AccRole.ADMIN);

    accountRepository.save(account);

    Account account1 =
        new Account(
            null,
            "Wilian Bueno",
            "Estudante de Python",
            "wil.bueno@gmail.com",
            "wilbueno",
            "Bio test",
            "https://github.com/wilbueno",
            "https://www.linkedin.com/in/wilbueno/",
            "123456",
            AccRole.COMPANYADMIN);
    accountRepository.save(account1);

    Account account2 =
        new Account(
            null,
            "Kennedy Bueno",
            "Estudante de Lumion",
            "kennedy.bueno@gmail.com",
            "kenbueno",
            "Bio test",
            "https://github.com/kenbueno",
            "https://www.linkedin.com/in/kenbueno/",
            "123456",
            AccRole.STUDENT);
    accountRepository.save(account2);

    courseRepository.deleteAll();
    Course course =
        new Course(
            null,
            "Front-End completo",
            "Curso completo de Front-End",
            "José Pereira",
            account1,
            "https://www.frontendcompleto.com.br",
            100.0,
            25.0,
            "frontend-completo",
            CourseStatus.APPROVED);

    Course course1 =
        new Course(
            null,
            "Back-End completo",
            "Curso completo de Back-End",
            "José Pereira",
            account1,
            "https://www.backendcompleto.com.br",
            150.0,
            50.0,
            "backend-completo",
            CourseStatus.PENDING);

    Course course2 =
        new Course(
            null,
            "UX completo",
            "Curso completo de UX",
            "José Pereira",
            account1,
            "https://www.uxcompleto.com.br",
            200.0,
            75.0,
            "ux-completo",
            CourseStatus.REJECTED);

    courseRepository.saveAll(Arrays.asList(course, course1, course2));
  }
}
