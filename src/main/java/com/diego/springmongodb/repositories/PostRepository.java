package com.diego.springmongodb.repositories;

import com.diego.springmongodb.domain.Post;
import com.diego.springmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
