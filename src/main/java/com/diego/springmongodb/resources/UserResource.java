package com.diego.springmongodb.resources;

import com.diego.springmongodb.domain.User;
import com.diego.springmongodb.dto.UserDTO;
import com.diego.springmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> users = service.findAll();
        List<UserDTO> usersDto = users
                .stream()
                .map(x -> new UserDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User obj = service.fromDTO(objDto);
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable String id){
        User obj = service.findById(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<User> update(@RequestBody UserDTO objDto, @PathVariable String id){

        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();


    }


}
