package com.example.test;

import com.example.test.model.Comment;
import com.example.test.model.Post;
import com.example.test.repository.CommentRepository;
import com.example.test.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	CommandLineRunner setup(PostRepository postRepository, CommentRepository commentRepository) {
		return args -> {
//			Post post = Post.builder().title("Post 1").description("Post 1 description").content("Post 1 content").comments(new HashSet<>()).build();
			Post post = new Post();
			post.setTitle("Post 1");
			post.setDescription("Post 1 description");
			post.setContent("Post 1 content");


//			Comment comment1 = new Comment(null, "Great Post!", null);
			Comment comment1 = new Comment();
			comment1.setText("Great Post!");
			comment1.setPost(post);
//			Comment comment2 = new Comment(null, "Really helpful Post, Thanks a lot", null);
			Comment comment2 = new Comment();
			comment2.setText("Really helpful Post, Thanks a lot");
			comment2.setPost(post);

			post.getComments().add(comment1);
			post.getComments().add(comment2);

			postRepository.save(post);
		};
	}
}
