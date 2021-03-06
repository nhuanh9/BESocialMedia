package com.example.media.service;

import com.example.media.model.User;
import com.example.media.model.entity.FriendEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    Iterable<User> findAll();

    User findByUsername(String username);

    User getCurrentUser();

    Optional<User> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(User user);

    boolean isRegister(User user);

    User findByEmail(String email);

    boolean isCorrectConfirmPassword(User user);

    void delete(User user);
}
