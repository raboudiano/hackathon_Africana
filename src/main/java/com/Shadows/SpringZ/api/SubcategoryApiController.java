package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.SubcategoryRequest;
import com.Shadows.SpringZ.api.dto.SubcategoryResponse;
import com.Shadows.SpringZ.model.Category;
import com.Shadows.SpringZ.model.Subcategory;
import com.Shadows.SpringZ.service.CategoryService;
import com.Shadows.SpringZ.service.SubcategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Subcategory CRUD.
 */
@RestController
@RequestMapping(value = "/api/subcategories", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubcategoryApiController {

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    public SubcategoryApiController(SubcategoryService subcategoryService, CategoryService categoryService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<SubcategoryResponse> list() {
        return subcategoryService.getAllSubcategories().stream()
                .map(SubcategoryApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public SubcategoryResponse get(@PathVariable Long id) {
        return toResponse(subcategoryService.getSubcategoryByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubcategoryResponse> create(@RequestBody SubcategoryRequest request) {
        Subcategory subcategory = new Subcategory();
        apply(subcategory, request);
        Subcategory saved = subcategoryService.createSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubcategoryResponse update(@PathVariable Long id, @RequestBody SubcategoryRequest request) {
        Subcategory existing = subcategoryService.getSubcategoryByID(id);
        apply(existing, request);
        return toResponse(subcategoryService.updateSubcategory(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }

    private void apply(Subcategory subcategory, SubcategoryRequest request) {
        subcategory.setTitle(request.title());
        subcategory.setDescription(request.description());

        if (request.categoryId() != null) {
            Category category = categoryService.getCategoryByID(request.categoryId());
            subcategory.setCategory(category);
        } else {
            subcategory.setCategory(null);
        }
    }

    private static SubcategoryResponse toResponse(Subcategory subcategory) {
        Long categoryId = subcategory.getCategory() == null ? null : subcategory.getCategory().getId();
        return new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getTitle(),
                subcategory.getDescription(),
                categoryId
        );
    }
}
