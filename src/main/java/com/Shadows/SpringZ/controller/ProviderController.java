package com.Shadows.SpringZ.controller;

import com.Shadows.SpringZ.model.Provider;
import com.Shadows.SpringZ.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/addProvider")
    public String addProvider(Model model) {
        model.addAttribute("ProviderFrom", new Provider());
        return "new_provider";
    }

    @PostMapping("/saveProvider")
    public String saveProvider(@ModelAttribute("ProviderFrom") Provider provider) {
        providerService.createProvider(provider);
        return "redirect:/allProviders";
    }

    @GetMapping("/allProviders")
    public String listProviders(Model model) {
        List<Provider> listProviders = providerService.getAllProviders();
        model.addAttribute("listProviders", listProviders);
        return "liste_providers";
    }

    @GetMapping("editProvider/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Provider provider = providerService.getProviderByID(id);
        model.addAttribute("provider", provider);
        return "update_provider";
    }

    @PostMapping("updateProvider/{id}")
    public String updateProvider(
            @PathVariable("id") long id,
            Provider provider,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            provider.setId(id);
            return "update_provider";
        }

        providerService.updateProvider(provider);
        return "redirect:/allProviders";
    }

    @GetMapping("deleteProvider/{id}")
    public String deleteProvider(@PathVariable("id") long id) {
        providerService.deleteProvider(id);
        return "redirect:/allProviders";
    }
}
