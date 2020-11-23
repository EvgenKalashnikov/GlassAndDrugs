package com.pharmacy.optican.demo.service;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.repository.UserRepository;
import com.pharmacy.optican.demo.security.UserSecurity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    Logger logger = LogManager.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        if (validateUser(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            try {
                userRepository.save(user);
                setAuth(user);
            } catch (Exception e) {
                logger.error("save error");
            }
        }
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElse(null);
    }

    public User findUserByPhone(String phone) {
        Optional<User> user = userRepository.findUserByPhone(phone);
        return user.orElse(null);
    }

    @PreAuthorize("isAuthenticated()")
    public void updateUser(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        oldUser.ifPresent(u -> {

            if (!user.getEmail().isEmpty() && !user.getEmail().equals(u.getEmail())) {
                u.setEmail(user.getEmail());
            }
            if (!user.getPhone().isEmpty() && !user.getPhone().equals(u.getPhone())) {
                u.setPhone(user.getPhone());
            }
            if (!user.getFullName().equals(u.getFullName())) {
                u.setFullName(user.getFullName());
            }
            if (!user.getPassword().isEmpty() && !user.getPassword().equals(u.getPassword())) {
                u.setPassword(user.getPassword());
                saveUser(u);
            } else {
                try {
                    userRepository.save(u);
                    setAuth(u);
                } catch (Exception e) {
                    logger.error("update error");
                }

            }

        });
    }

    private boolean validateUser(User user) {
        return !user.getEmail().isEmpty()
                && !user.getPhone().isEmpty()
                && !user.getPassword().isEmpty();

    }

    private void setAuth(User user) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(), user.getPassword(), new UserSecurity(user).getAuthorities()));
    }

}

