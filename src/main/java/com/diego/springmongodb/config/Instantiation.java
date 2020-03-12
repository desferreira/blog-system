package com.diego.springmongodb.config;

import com.diego.springmongodb.domain.Post;
import com.diego.springmongodb.domain.User;
import com.diego.springmongodb.dto.AuthorDTO;
import com.diego.springmongodb.dto.CommentDTO;
import com.diego.springmongodb.repositories.PostRepository;
import com.diego.springmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));


        userRepository.deleteAll();
        postRepository.deleteAll();

        User diego = new User(null, "Diego", "diego@gmail.com");
        User leka = new User(null, "Leka", "leka@gmail.com");

        userRepository.saveAll(Arrays.asList(diego, leka));

        Post post1 = new Post(null, new Date(), "Primeiro post", "Finalmente primeiro ost", new AuthorDTO(diego));
        Post post2 = new Post(null, new Date(), "Segundo post", "Finalmente segundo ost", new AuthorDTO(leka));
        Post post3 = new Post(null, new Date(), "Terceiro post", "Finalmente terceiro ost", new AuthorDTO(diego));

        CommentDTO c1 = new CommentDTO("Que massa!", sdf.parse("10/03/2020"), new AuthorDTO(diego));
        CommentDTO c2 = new CommentDTO("TOP!!", sdf.parse("02/03/2020"), new AuthorDTO(leka));

        postRepository.saveAll(Arrays.asList(post1, post2, post3));

        post1.setComments(c1);
        post2.setComments(c2);

        postRepository.saveAll(Arrays.asList(post1, post2, post3));
        userRepository.saveAll(Arrays.asList(diego, leka));

        diego.getPosts().addAll(Arrays.asList(post1, post3));
        leka.getPosts().addAll(Arrays.asList(post2));

    }
}
