package com.pharmacy.optican.demo.service;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.repository.UserRepository;
import com.pharmacy.optican.demo.security.UserSecurity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
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
        if (validateUser(user) && validatePhone(user.getPhone()) && validateEmail(user.getEmail())) {
            trimAndLowerCase(user);
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

            if (!StringUtils.isBlank(user.getEmail()) && !user.getEmail().equals(u.getEmail()) && validateEmail(user.getEmail())) {
                u.setEmail(user.getEmail().trim().toLowerCase());
            }
            if (!StringUtils.isBlank(user.getPhone()) && !user.getPhone().equals(u.getPhone()) && validatePhone(user.getPhone())) {
                u.setPhone(user.getPhone().trim());
            }
            if (user.getFullName() != null && !user.getFullName().equals(u.getFullName())) {
                u.setFullName(user.getFullName().trim());
            }
            if (!StringUtils.isBlank(user.getPassword()) && !passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                u.setPassword(passwordEncoder.encode(user.getPassword()).trim());
            }

            try {
                userRepository.save(u);
                setAuth(u);
            } catch (Exception e) {
                logger.error("update error");
            }


        });
    }

    private boolean validateUser(User user) {
        return !StringUtils.isAnyBlank(user.getEmail(), user.getPhone(), user.getPassword());
    }

    private void setAuth(User user) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(), user.getPassword(), new UserSecurity(user).getAuthorities()));
    }


    private boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean validatePhone(String phone) {
        return StringUtils.isNumeric(phone) && phone.length() <= 12;
    }

    private void trimAndLowerCase(User user) {
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setPassword(user.getPassword().trim());
    }
}

