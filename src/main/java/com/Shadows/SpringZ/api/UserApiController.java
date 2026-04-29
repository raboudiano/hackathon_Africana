package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.UserRequest;
import com.Shadows.SpringZ.api.dto.UserResponse;
import com.Shadows.SpringZ.model.User;
import com.Shadows.SpringZ.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for base User CRUD.
 *
 * Design note:
 * - Provider and Customer are separate resources with their own endpoints.
 * - This controller is for the base User entity.
 */
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> list() {
        return userService.getAllUsers().stream()
                .map(UserApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return toResponse(userService.getUserByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        User user = new User();
        apply(user, request);
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
        User existing = userService.getUserByID(id);
        apply(existing, request);
        return toResponse(userService.updateUser(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private static void apply(User user, UserRequest request) {
        user.setName(request.name());
        user.setSalary(request.salary());
        user.setPhone(request.phone());
        user.setAge(request.age());
        user.setEmail(request.email());
        user.setPassword(request.password());
    }

    private static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSalary(),
                user.getPhone(),
                user.getAge(),
                user.getEmail()
        );
    }
}
