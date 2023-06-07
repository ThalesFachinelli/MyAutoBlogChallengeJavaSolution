package challenge.service;

import challenge.model.Post;
import challenge.model.User;
import challenge.repository.PostRepository;
import challenge.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    private AuthenticationFacade facade;

    public Post createPost(Post post) {
        User user = facade.getUser();
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        User user = facade.getUser();
        return postRepository.getByUser(user);
    }

    public Post updatePost(@PathVariable long id, @RequestBody Post post) {
        Optional<Post> preExistingPost = postRepository.findById(id);
        if(!preExistingPost.isEmpty()) {
            Post updatedPost = new Post(
                    preExistingPost.get().getId(),
                    preExistingPost.get().getDate(),
                    post.getTitle(),
                    post.getText(),
                    preExistingPost.get().getUser()
            );
            return postRepository.save(updatedPost);
        }
        return null;
    }

    public void deletePost(@PathVariable long id) {
        postRepository.deleteById(id);
    }
}
