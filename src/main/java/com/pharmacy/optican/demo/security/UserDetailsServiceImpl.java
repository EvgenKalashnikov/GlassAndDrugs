package com.pharmacy.optican.demo.security;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {

        User user = userService.findUserByEmail(emailOrPhone);
        if (user == null) {
            user = userService.findUserByPhone(emailOrPhone);
        }
        if (user != null) {
            return new UserSecurity(user);
        }
        throw new UsernameNotFoundException("no user");
    }
}
