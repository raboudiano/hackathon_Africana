package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);

    // Primary API (conventional naming).
    List<User> getAllUsers();

    // Backward-compatible alias for earlier versions.
    @Deprecated
    default List<User> getALLUser() {
        return getAllUsers();
    }

    User getUserByID(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
