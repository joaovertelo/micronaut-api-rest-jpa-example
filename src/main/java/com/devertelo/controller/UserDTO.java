package com.devertelo.controller;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;

@Introspected
@SerdeImport(UserDTO.class)
public record UserDTO(Long id, String username, String password) {
}
