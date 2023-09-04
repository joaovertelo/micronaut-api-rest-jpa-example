package com.devertelo.controller;

import com.fasterxml.jackson.annotation.JsonClassDescription;

@JsonClassDescription
public record UserDTO(Long id, String username, String password) {
}
