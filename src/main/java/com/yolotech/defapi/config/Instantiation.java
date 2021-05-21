package com.yolotech.defapi.config;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.domain.enums.AccRole;
import com.yolotech.defapi.repositories.AccountRepository;
import com.yolotech.defapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  private final AccountRepository accountRepository;

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
            AccRole.ADMIN,
            true);

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
            AccRole.COMPANYADMIN,
            true);
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
            AccRole.STUDENT,
            true);
    accountRepository.save(account2);
  }
}
