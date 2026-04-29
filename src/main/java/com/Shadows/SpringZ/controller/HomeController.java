package com.Shadows.SpringZ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Minimal home page controller.
 * Responsibility: provide a single landing page that links to existing list pages.
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "index";
    }
}
