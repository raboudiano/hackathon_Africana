package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.CategoryRequest;
import com.Shadows.SpringZ.api.dto.CategoryResponse;
import com.Shadows.SpringZ.model.Category;
import com.Shadows.SpringZ.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Category CRUD.
 */
@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> list() {
        return categoryService.getAllCategories().stream()
                .map(CategoryApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable Long id) {
        return toResponse(categoryService.getCategoryByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        Category category = new Category();
        apply(category, request);
        Category saved = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest request) {
        Category existing = categoryService.getCategoryByID(id);
        apply(existing, request);
        return toResponse(categoryService.updateCategory(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    private static void apply(Category category, CategoryRequest request) {
        category.setTitle(request.title());
        category.setDescription(request.description());
    }

    private static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getTitle(), category.getDescription());
    }
}
