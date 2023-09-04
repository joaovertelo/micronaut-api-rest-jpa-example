package com.devertelo.domain;

import com.devertelo.controller.UserDTO;
import com.devertelo.infrastructure.UserRepository;
import io.micronaut.context.annotation.Bean;

import java.util.List;

@Bean
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(it -> new UserDTO(it.getId(), it.getUsername(), it.getPassword()))
                .toList();
    }


}
