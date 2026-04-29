package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Provider;

import java.util.List;

/**
 * Service Layer for Provider CRUD.
 */
public interface ProviderService {
    Provider createProvider(Provider provider);

    List<Provider> getAllProviders();

    Provider getProviderByID(Long id);

    Provider updateProvider(Provider provider);

    void deleteProvider(Long id);
}
