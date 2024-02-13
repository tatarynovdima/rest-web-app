package com.example.restwebapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.restwebapp.dto.UserDTO;
import com.example.restwebapp.dto.RoleDTO;

import java.util.Collection;
import java.util.stream.Collectors;


public class CustomUserDetails implements UserDetails {

    private final UserDTO user;

    public CustomUserDetails(UserDTO user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.roles().stream()
                .map(RoleDTO::name)
                .map(roleName -> (GrantedAuthority) () -> roleName)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.firstName() + " " + user.lastName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
