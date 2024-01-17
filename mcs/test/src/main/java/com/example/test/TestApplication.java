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
			Post post = Post.builder()
					.title("Post 1")
					.description("Post 1 description")
					.content("Post 1 content")
					.comments(new HashSet<>())
					.build();


			Comment comment1 = Comment.builder()
					.text("Great Post!")
					.post(post)
					.build();
//			comment1.setPost(post);

			Comment comment2 = Comment.builder()
					.text("Really helpful Post, Thanks a lot")
					.post(post)
					.build();
//			comment2.setPost(post);

			post.getComments().add(comment1);
			post.getComments().add(comment2);

			postRepository.save(post);
		};
	}
}
