package com.diego.springmongodb.services;

import com.diego.springmongodb.domain.Post;
import com.diego.springmongodb.repositories.PostRepository;
import com.diego.springmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        if (post == null){
            throw new ResourceNotFoundException(id);
        }

        return post.get();
    }

    public List<Post> findByTitle(String text){
        return repository.findByTitleContainingIgnoringCase(text);

    }

}
