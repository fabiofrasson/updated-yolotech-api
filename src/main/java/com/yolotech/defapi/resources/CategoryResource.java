package com.yolotech.defapi.resources;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.dto.category.CategoryDTOPost;
import com.yolotech.defapi.dto.category.CategoryDTOPut;
import com.yolotech.defapi.services.CategoryService;
import com.yolotech.defapi.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("categories")
@Log4j2
@RequiredArgsConstructor
public class CategoryResource {
    private final DateUtil dateUtil;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        log.info(dateUtil.formatLocalDateTimetoDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(categoryService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody @Valid CategoryDTOPost categoryDTOPost) {
        return new ResponseEntity<>(categoryService.save(categoryDTOPost), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody CategoryDTOPut categoryDTOPut) {
        categoryService.replace(categoryDTOPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
