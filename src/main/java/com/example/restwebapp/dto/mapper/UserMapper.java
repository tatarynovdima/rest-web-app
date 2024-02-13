package com.example.restwebapp.dto.mapper;

import com.example.restwebapp.dto.RoleDTO;
import com.example.restwebapp.dto.UserDTO;
import com.example.restwebapp.entity.User;
import com.example.restwebapp.entity.Role;
import com.example.restwebapp.entity.RoleType;

import java.util.Set;

import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setPassword(userDTO.password());

        Set<Role> roles = userDTO.roles().stream()
                .map(roleDTO -> new Role(roleDTO.id(), RoleType.valueOf(roleDTO.name())))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return user;
    }

    public static UserDTO toDTO(User user) {
        Set<RoleDTO> roleDTOs = user.getRoles().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName().toString()))
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), roleDTOs);
    }
}
