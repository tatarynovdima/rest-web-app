package com.example.restwebapp.service.Impl;

import com.example.restwebapp.dto.UserDTO;
import com.example.restwebapp.dto.mapper.UserMapper;
import com.example.restwebapp.entity.User;
import com.example.restwebapp.repository.UserRepository;
import com.example.restwebapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        lock.lock();
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserMapper::toDTO)
                    .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(UserMapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getByUsername(String firstName) {
        Optional<User> userOptional = userRepository.findByFirstName(firstName);
        return userOptional.map(UserMapper::toDTO);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setFirstName(userDTO.firstName());
            userToUpdate.setLastName(userDTO.lastName());
            userToUpdate.setPassword(userDTO.password());

            userRepository.save(userToUpdate);
            return UserMapper.toDTO(userToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
