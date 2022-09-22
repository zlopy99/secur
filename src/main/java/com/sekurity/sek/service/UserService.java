package com.sekurity.sek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = new ArrayList<>();
        users.add(new User("admin", passwordEncoder.encode("password"), Collections.singleton(new SimpleGrantedAuthority("ADMIN"))));
        users.add(new User("user1", "password", Collections.singleton(new SimpleGrantedAuthority("USER"))));
        users.add(new User("user2", "password", Collections.singleton(new SimpleGrantedAuthority("USER"))));

        for (User user: users) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }


//        return new User("admin", "password", Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
        return null;
    }
}
