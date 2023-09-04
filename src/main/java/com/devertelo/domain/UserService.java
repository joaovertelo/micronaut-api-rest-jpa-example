package com.devertelo.domain;

import com.devertelo.controller.UserDTO;
import com.devertelo.infrastructure.User;
import com.devertelo.infrastructure.UserRepository;
import io.micronaut.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@Bean
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(it -> new UserDTO(it.getId(), it.getUsername(), it.getPassword()))
                .toList();
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(it -> new UserDTO(it.getId(), it.getUsername(), it.getPassword()));
    }

    public UserDTO create(UserDTO userDTO) {
        var user = userRepository.save(new User(userDTO.username(), userDTO.password()));
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
    }

    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        var userById = userRepository.findById(id);
        return userById.map(user -> {
            user.setUsername(userDTO.username());
            user.setPassword(userDTO.password());
            var userUpdated = userRepository.update(user);
            return new UserDTO(userUpdated.getId(), userUpdated.getUsername(), userUpdated.getPassword());
        });
    }

    public boolean deleteById(Long id) {
        var userById = userRepository.findById(id);
        if (userById.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
