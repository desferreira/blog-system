package com.diego.springmongodb.config;

import com.diego.springmongodb.domain.User;
import com.diego.springmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        userRepository.deleteAll();

        User diego = new User(null, "Diego", "diego@gmail.com");
        User leka = new User(null, "Leka", "leka@gmail.com");

        userRepository.save(diego);
        userRepository.save(leka);


    }
}
