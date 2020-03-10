package com.diego.springmongodb.services;

import com.diego.springmongodb.domain.User;
import com.diego.springmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public void insert(User obj){
        repository.insert(obj);
    }

}
