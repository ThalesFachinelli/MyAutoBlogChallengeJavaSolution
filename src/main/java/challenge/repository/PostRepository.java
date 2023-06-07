package challenge.repository;

import challenge.model.User;
import org.springframework.data.repository.CrudRepository;

import challenge.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> getByUser(User user);
	
}
