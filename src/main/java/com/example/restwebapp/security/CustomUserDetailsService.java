package com.example.restwebapp.security;

import com.example.restwebapp.dto.UserDTO;
import com.example.restwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<UserDTO> userDTOOptional = userService.getByUsername(firstName);
        UserDTO userDTO = userDTOOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with firstName: " + firstName));
        return new CustomUserDetails(userDTO);
    }
}