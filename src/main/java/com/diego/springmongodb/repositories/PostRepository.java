package com.diego.springmongodb.repositories;

import com.diego.springmongodb.domain.Post;
import com.diego.springmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoringCase(String text);

    @Query("{ 'title': {$regex: ?0, $options: 'i'} }")
    List<Post> findByTitle(String text);
}
