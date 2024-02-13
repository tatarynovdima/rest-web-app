package com.example.restwebapp.service;

import com.example.restwebapp.dto.UserDTO;
import com.example.restwebapp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    Optional<UserDTO> getById(Long id);
    Optional<UserDTO> getByUsername(String username);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
