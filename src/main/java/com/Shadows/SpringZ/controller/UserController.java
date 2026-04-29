package com.Shadows.SpringZ.controller;

import com.Shadows.SpringZ.model.User;
import com.Shadows.SpringZ.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("UserFrom", new User());
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("UserFrom") User user) {
        userService.createUser(user);
        return "redirect:/allUser";
    }

    @GetMapping("/allUser")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listusers", listUsers);
        return "liste_user";
    }

    @GetMapping("edituser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getUserByID(id);
        model.addAttribute("users", user);
        return "update_user";
    }

    @PostMapping("updateUser/{id}")
    public String updateUser(
            @PathVariable("id") long id,
            User user,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update_user";
        }

        userService.updateUser(user);
        return "redirect:/allUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/allUser";
    }
}
