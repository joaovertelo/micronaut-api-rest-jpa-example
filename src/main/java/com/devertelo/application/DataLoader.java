package com.devertelo.application;

import com.devertelo.infrastructure.User;
import com.devertelo.infrastructure.UserRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        userRepository.save(new User("nome", " senha"));

    }
}
