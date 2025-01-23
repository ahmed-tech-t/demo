package com.example.demo.service;

import com.example.demo.entity.MyUser;
import com.example.demo.entity.UserPrincipal;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDetailsService implements UserDetailsService {
    UserRepo repo;

    @Autowired
    MyDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repo.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user.getUsername(), user.getPassword(), user.getRole());
    }
}
