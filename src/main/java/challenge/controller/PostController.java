package challenge.controller;

import challenge.model.Post;
import challenge.model.User;
import challenge.security.AuthenticationFacade;
import challenge.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import challenge.repository.PostRepository;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

	@Autowired
	private PostRepository repository;

	@Autowired
	private PostService postService;
	
	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postService.createPost(post);
	}

	@GetMapping("/posts")
	public List<Post> getPosts() {
		return postService.getPosts();
	}

	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable long id, @RequestBody Post post) {
		return postService.updatePost(id, post);
	}

	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable long id) {
		postService.deletePost(id);
	}
}
