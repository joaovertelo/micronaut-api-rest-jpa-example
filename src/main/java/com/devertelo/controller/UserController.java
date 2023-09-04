package com.devertelo.controller;

import com.devertelo.domain.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Get
    public HttpResponse<List<UserDTO>> findAll() {
        return HttpResponse.ok(userService.findAll());
    }

    @Post
    public HttpResponse<UserDTO> create(@Body UserDTO userDTO) {
        return HttpResponse.created(userService.create(userDTO));
    }

    @Get("/{id}")
    public HttpResponse<UserDTO> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        if (user.isEmpty()) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(user.get());
    }

    @Put("/{id}")
    public HttpResponse<UserDTO> update(@PathVariable Long id, @Body UserDTO userDTO) {
        Optional<UserDTO> user = userService.update(id, userDTO);
        if (user.isEmpty()) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(user.get());
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteById(@PathVariable Long id) {
        var deleted = userService.deleteById(id);

        return deleted ? HttpResponse.ok() : HttpResponse.notFound();
    }
}
