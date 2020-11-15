package com.pharmacy.optican.demo.security;

import com.pharmacy.optican.demo.model.User;
import com.pharmacy.optican.demo.repository.UserRepository;
import com.pharmacy.optican.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.findUserByName(username);

        if(user.isPresent()){
            return new UserSecurity(user.get());
        }
        throw new UsernameNotFoundException("no user");
    }
}
