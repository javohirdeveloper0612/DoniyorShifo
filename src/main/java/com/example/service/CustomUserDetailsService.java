package com.example.service;

import com.example.config.CustomUserDetail;
import com.example.entity.AdminEntity;
import com.example.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository repository;

    public CustomUserDetailsService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AdminEntity> optional = repository.findByUsername(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bad Credentials");
        }

        return new CustomUserDetail(optional.get());
    }
}
