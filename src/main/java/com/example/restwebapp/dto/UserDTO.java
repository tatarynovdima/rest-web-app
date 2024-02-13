package com.example.restwebapp.dto;

import java.util.Set;


public record UserDTO(Long id, String firstName, String lastName, String password, Set<RoleDTO> roles) {}


