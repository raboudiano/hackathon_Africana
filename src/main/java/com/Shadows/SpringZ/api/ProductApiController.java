package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.ProductRequest;
import com.Shadows.SpringZ.api.dto.ProductResponse;
import com.Shadows.SpringZ.model.Product;
import com.Shadows.SpringZ.service.ProductService;
import com.Shadows.SpringZ.service.ProviderService;
import com.Shadows.SpringZ.service.SubcategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Product CRUD.
 *
 * DTOs are used to avoid JSON recursion from bidirectional JPA mappings.
 */
@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductApiController {

    private final ProductService productService;
    private final ProviderService providerService;
    private final SubcategoryService subcategoryService;

    public ProductApiController(
            ProductService productService,
            ProviderService providerService,
            SubcategoryService subcategoryService
    ) {
        this.productService = productService;
        this.providerService = providerService;
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public List<ProductResponse> list() {
        return productService.getAllProducts().stream()
                .map(ProductApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return toResponse(productService.getProductByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        Product product = new Product();
        apply(product, request);
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest request) {
        Product existing = productService.getProductByID(id);
        apply(existing, request);
        return toResponse(productService.updateProduct(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private void apply(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());

        if (request.providerId() != null) {
            product.setProvider(providerService.getProviderByID(request.providerId()));
        } else {
            product.setProvider(null);
        }

        if (request.subcategoryId() != null) {
            product.setSubcategory(subcategoryService.getSubcategoryByID(request.subcategoryId()));
        } else {
            product.setSubcategory(null);
        }
    }

    private static ProductResponse toResponse(Product product) {
        Long providerId = product.getProvider() == null ? null : product.getProvider().getId();
        Long subcategoryId = product.getSubcategory() == null ? null : product.getSubcategory().getId();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                providerId,
                subcategoryId
        );
    }
}
