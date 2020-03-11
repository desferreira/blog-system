package com.diego.springmongodb.services;

import com.diego.springmongodb.domain.User;
import com.diego.springmongodb.dto.UserDTO;
import com.diego.springmongodb.repositories.UserRepository;
import com.diego.springmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        if (user == null){
            throw new ResourceNotFoundException(id);
        }
        return user.get();
    }

    public void insert(User obj){
        repository.insert(obj);
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public void delete(String id){
        Optional<User> user = repository.findById(id);
        if (user == null){
            throw new ResourceNotFoundException(id);
        }
        repository.delete(user.get());
    }

    public User update(User obj){
        Optional<User> newObj = repository.findById(obj.getId());
        updateData(newObj.get(), obj);
        return repository.save(newObj.get());
    }

    private void updateData(User newObj, User obj) {

        newObj.setEmail(obj.getEmail());
        newObj.setName(obj.getName());

    }

}
