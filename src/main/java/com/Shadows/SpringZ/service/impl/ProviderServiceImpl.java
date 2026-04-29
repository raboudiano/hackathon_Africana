package com.Shadows.SpringZ.service.impl;

import com.Shadows.SpringZ.model.Provider;
import com.Shadows.SpringZ.repository.ProviderRepository;
import com.Shadows.SpringZ.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public Provider getProviderByID(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found: id=" + id));
    }

    @Override
    public Provider updateProvider(Provider provider) {
        return providerRepository.saveAndFlush(provider);
    }

    @Override
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
