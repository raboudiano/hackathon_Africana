package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.ProviderRequest;
import com.Shadows.SpringZ.api.dto.ProviderResponse;
import com.Shadows.SpringZ.model.Provider;
import com.Shadows.SpringZ.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Provider CRUD.
 */
@RestController
@RequestMapping(value = "/api/providers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProviderApiController {

    private final ProviderService providerService;

    public ProviderApiController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<ProviderResponse> list() {
        return providerService.getAllProviders().stream()
                .map(ProviderApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProviderResponse get(@PathVariable Long id) {
        return toResponse(providerService.getProviderByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProviderResponse> create(@RequestBody ProviderRequest request) {
        Provider provider = new Provider();
        apply(provider, request);
        Provider saved = providerService.createProvider(provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProviderResponse update(@PathVariable Long id, @RequestBody ProviderRequest request) {
        Provider existing = providerService.getProviderByID(id);
        apply(existing, request);
        return toResponse(providerService.updateProvider(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    private static void apply(Provider provider, ProviderRequest request) {
        provider.setName(request.name());
        provider.setSalary(request.salary());
        provider.setPhone(request.phone());
        provider.setAge(request.age());
        provider.setEmail(request.email());
        provider.setPassword(request.password());
        provider.setMatricule(request.matricule());
        provider.setService(request.service());
        provider.setCompany(request.company());
    }

    private static ProviderResponse toResponse(Provider provider) {
        return new ProviderResponse(
                provider.getId(),
                provider.getName(),
                provider.getSalary(),
                provider.getPhone(),
                provider.getAge(),
                provider.getEmail(),
                provider.getMatricule(),
                provider.getService(),
                provider.getCompany()
        );
    }
}
